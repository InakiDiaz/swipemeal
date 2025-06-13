package com.swipemeal.security;

import com.swipemeal.model.User;
import com.swipemeal.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserRepository userRepository) {
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        // Si el token no está presente o es inválido, dejamos que el request siga
        if (shouldSkipFilter(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Ya garantizamos que hay token y username válido, ahora intentamos autenticar
        tryAuthenticate(request);
        filterChain.doFilter(request, response);
    }

    // Encapsula la lógica de validación temprana del token y username
    private boolean shouldSkipFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        // Excluir rutas públicas
        if (path.startsWith("/api/auth")) {
            return true;
        }

        String token = extractToken(request);
        if (token == null) return true;

        String username = jwtUtils.extractUsername(token);
        if (username == null || isAlreadyAuthenticated()) return true;

        request.setAttribute("jwt_token", token);
        request.setAttribute("jwt_username", username);
        return false;
    }


    // Encapsula el proceso de autenticación usando los atributos temporales
    private void tryAuthenticate(HttpServletRequest request) {
        String token = (String) request.getAttribute("jwt_token");
        String username = (String) request.getAttribute("jwt_username");

        userRepository.findByUsername(username).ifPresent(user -> {
            if (jwtUtils.isTokenValid(token, user)) {
                authenticate(user, request);
            }
        });
    }

    // Extrae el token JWT desde el header Authorization
    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    // Verifica si ya hay alguien autenticado en el contexto de seguridad
    private boolean isAlreadyAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

    // Realiza la autenticación manualmente estableciendo el contexto de seguridad
    private void authenticate(User user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}

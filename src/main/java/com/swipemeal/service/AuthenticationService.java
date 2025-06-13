package com.swipemeal.service;

import com.swipemeal.dto.auth.AuthRequest;
import com.swipemeal.dto.auth.AuthResponse;
import com.swipemeal.dto.auth.RegisterRequest;
import com.swipemeal.model.Role;
import com.swipemeal.model.User;
import com.swipemeal.repository.UserRepository;
import com.swipemeal.security.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthenticationService(UserRepository userRepo,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 JwtUtils jwtUtils) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public AuthResponse register(RegisterRequest req) {
        if (userRepo.findByUsername(req.username()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        User user = new User();
        user.setUsername(req.username());
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setRole(Role.USER); // O el rol que uses en tu proyecto

        userRepo.save(user);

        String token = jwtUtils.generateToken(user);
        return new AuthResponse(
                token,
                user.getUsername(),
                user.getRole().name()
        );
    }

    public AuthResponse login(AuthRequest req) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password())
        );

        User user = (User) auth.getPrincipal(); // Ya autenticado

        String token = jwtUtils.generateToken(user);
        return new AuthResponse(token, user.getUsername(), user.getRole().name());
    }
}
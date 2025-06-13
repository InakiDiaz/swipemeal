package com.swipemeal.controller;

import com.swipemeal.dto.CreateUserRequest;
import com.swipemeal.dto.UserDTO;
import com.swipemeal.model.User;
import com.swipemeal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .build();

        User saved = userService.save(user);

        UserDTO dto = new UserDTO(saved);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        UserDTO dto = new UserDTO(user);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        String username = org.springframework.security.core.context.SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userService.findByUsername(username);
        return ResponseEntity.ok(new UserDTO(user));
    }
}


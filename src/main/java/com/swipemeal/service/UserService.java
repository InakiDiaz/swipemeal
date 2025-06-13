package com.swipemeal.service;

import com.swipemeal.exception.NotFoundException;
import com.swipemeal.model.User;
import com.swipemeal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + username));
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario con ID " + id + " no encontrado"));
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("No se puede eliminar. Usuario con ID " + id + " no existe");
        }
        userRepository.deleteById(id);
    }
}

package com.swipemeal.dto;

import com.swipemeal.model.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;

    public  UserDTO(User user) {
        UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
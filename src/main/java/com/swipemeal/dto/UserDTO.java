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

    public static UserDTO fromEntity(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
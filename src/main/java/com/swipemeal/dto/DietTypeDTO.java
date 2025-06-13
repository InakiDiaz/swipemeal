package com.swipemeal.dto;

import com.swipemeal.model.DietType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DietTypeDTO {
    private Long id;
    private String name;

    public static DietTypeDTO fromEntity(DietType dietType) {
        return DietTypeDTO.builder()
                .id(dietType.getId())
                .name(dietType.getName())
                .build();
    }
}

package com.swipemeal.dto;

import com.swipemeal.model.Ingredient;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientDTO {
    private Long id;
    private String name;

    public IngredientDTO (Ingredient ingredient) {
        IngredientDTO.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .build();
    }
}


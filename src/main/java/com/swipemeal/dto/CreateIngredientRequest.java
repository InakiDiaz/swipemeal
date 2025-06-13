package com.swipemeal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateIngredientRequest {
    @NotBlank
    private String name;
}

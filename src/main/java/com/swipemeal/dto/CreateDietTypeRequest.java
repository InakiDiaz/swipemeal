package com.swipemeal.dto;

import com.swipemeal.model.DietType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDietTypeRequest {
    @NotBlank
    private String name;
}

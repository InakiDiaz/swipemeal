package com.swipemeal.dto;

import com.swipemeal.model.Recipe;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class RecipeDTO {
    private Long id;
    private String title;
    private String description;
    private Integer prepTime;
    private CategoryDTO category;
    private DietTypeDTO dietType;
    private List<IngredientDTO> ingredients;

    public static RecipeDTO fromEntity(Recipe recipe) {
        return RecipeDTO.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .prepTime(recipe.getPrepTime())
                .category(CategoryDTO.fromEntity(recipe.getCategory()))
                .dietType(DietTypeDTO.fromEntity(recipe.getDietType()))
                .ingredients(recipe.getIngredients().stream()
                        .map(IngredientDTO::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}

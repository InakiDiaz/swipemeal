package com.swipemeal.dto;

import com.swipemeal.model.FilterPreference;
import com.swipemeal.model.Ingredient;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterPreferenceDTO {
    private Long categoryId;
    private Long dietTypeId;
    private Integer maxPrepTime;
    private List<Long> availableIngredientIds;

    public FilterPreferenceDTO (FilterPreference filterPreference){
        this.categoryId = filterPreference.getCategory().getId();
        this.dietTypeId = filterPreference.getDiet().getId();
        this.maxPrepTime = filterPreference.getMaxPrepTime();
        this.availableIngredientIds = filterPreference.getAvailableIngredients().
                stream().map(Ingredient::getId).toList();
    }
}

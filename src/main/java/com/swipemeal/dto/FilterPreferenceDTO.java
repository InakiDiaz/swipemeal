package com.swipemeal.dto;

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
}

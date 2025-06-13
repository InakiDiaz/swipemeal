package com.swipemeal.service;

import com.swipemeal.exception.NotFoundException;
import com.swipemeal.model.FilterPreference;
import com.swipemeal.model.Recipe;
import com.swipemeal.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Recipe findById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Receta con ID " + id + " no encontrada"));
    }

    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getFilteredSwipeRecipes(FilterPreference pref) {
        return recipeRepository.findAll().stream()
                .filter(r -> pref.getCategory() == null || r.getCategory().equals(pref.getCategory()))
                .filter(r -> pref.getDiet() == null || r.getDietType().equals(pref.getDiet()))
                .filter(r -> pref.getMaxPrepTime() == null || r.getPrepTime() <= pref.getMaxPrepTime())
                .filter(r -> pref.getAvailableIngredients() == null ||
                        pref.getAvailableIngredients().containsAll(r.getIngredients()))
                .collect(Collectors.toList());
    }
}

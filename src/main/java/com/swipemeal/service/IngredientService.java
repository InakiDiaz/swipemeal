package com.swipemeal.service;

import com.swipemeal.exception.NotFoundException;
import com.swipemeal.model.Ingredient;
import com.swipemeal.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient findByName(String name) {
        return ingredientRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Ingrediente no encontrado: " + name));
    }

    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ingrediente con ID " + id + " no encontrado"));
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }
}

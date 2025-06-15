package com.swipemeal.controller;

import com.swipemeal.dto.CreateIngredientRequest;
import com.swipemeal.dto.IngredientDTO;
import com.swipemeal.model.Ingredient;
import com.swipemeal.service.IngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<IngredientDTO> createIngredient(@Valid @RequestBody CreateIngredientRequest request) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(request.getName());

        Ingredient ingredientSaved = ingredientService.save(ingredient);

        IngredientDTO dto = new IngredientDTO(ingredientSaved);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<IngredientDTO>> getAll() {
        List<IngredientDTO> list = ingredientService.findAll().stream()
                .map(IngredientDTO::new)
                .toList();

        return ResponseEntity.ok(list);
    }
}

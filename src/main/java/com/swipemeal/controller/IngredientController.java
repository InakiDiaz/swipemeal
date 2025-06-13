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

        Ingredient saved = ingredientService.save(ingredient);

        IngredientDTO dto = IngredientDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .build();

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<IngredientDTO>> getAll() {
        List<IngredientDTO> list = ingredientService.findAll().stream()
                .map(i -> IngredientDTO.builder()
                        .id(i.getId())
                        .name(i.getName())
                        .build())
                .toList();

        return ResponseEntity.ok(list);
    }
}

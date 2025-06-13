package com.swipemeal.controller;

import com.swipemeal.dto.CategoryDTO;
import com.swipemeal.dto.CreateCategoryRequest;
import com.swipemeal.model.Category;
import com.swipemeal.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CreateCategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());

        Category saved = categoryService.save(category);

        CategoryDTO dto = CategoryDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .build();

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> list = categoryService.findAll().stream()
                .map(c -> CategoryDTO.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .build())
                .toList();

        return ResponseEntity.ok(list);
    }


}

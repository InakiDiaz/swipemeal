package com.swipemeal.controller;

import com.swipemeal.dto.CreateDietTypeRequest;
import com.swipemeal.dto.DietTypeDTO;
import com.swipemeal.model.DietType;
import com.swipemeal.service.DietTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diet-types")
@RequiredArgsConstructor
public class DietTypeController {

    private final DietTypeService dietTypeService;

    @PostMapping
    public ResponseEntity<DietTypeDTO> create(@Valid @RequestBody CreateDietTypeRequest request) {
        DietType diet = new DietType();
        diet.setName(request.getName());

        DietType saved = dietTypeService.save(diet);

        DietTypeDTO dto = DietTypeDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .build();

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<DietTypeDTO>> getAll() {
        List<DietTypeDTO> list = dietTypeService.findAll().stream()
                .map(d -> DietTypeDTO.builder()
                        .id(d.getId())
                        .name(d.getName())
                        .build())
                .toList();

        return ResponseEntity.ok(list);
    }
}

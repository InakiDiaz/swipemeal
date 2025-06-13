package com.swipemeal.service;

import com.swipemeal.exception.NotFoundException;
import com.swipemeal.model.DietType;
import com.swipemeal.model.Recipe;
import com.swipemeal.repository.DietTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DietTypeService {

    private final DietTypeRepository dietTypeRepository;

    public DietType save(DietType dietType) {
        return dietTypeRepository.save(dietType);
    }

    public DietType findByName(String name) {
        return dietTypeRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Tipo de dieta no encontrado: " + name));
    }

    public DietType findById(Long id) {
        return dietTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tipo de dieta con ID " + id + " no encontrado"));
    }

    public List<DietType> findAll() {
        return dietTypeRepository.findAll();
    }
}

package com.swipemeal.service;

import com.swipemeal.exception.NotFoundException;
import com.swipemeal.model.Category;
import com.swipemeal.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada: " + name));
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoría con ID " + id + " no encontrada"));
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}

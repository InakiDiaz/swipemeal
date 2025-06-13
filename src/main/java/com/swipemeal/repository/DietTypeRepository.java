package com.swipemeal.repository;

import com.swipemeal.model.DietType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DietTypeRepository extends JpaRepository<DietType, Long> {
    Optional<DietType> findByName(String name);
}

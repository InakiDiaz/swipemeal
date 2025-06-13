package com.swipemeal.repository;

import com.swipemeal.model.FilterPreference;
import com.swipemeal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilterPreferenceRepository extends JpaRepository<FilterPreference, Long> {
    Optional<FilterPreference> findByUser(User user);
}

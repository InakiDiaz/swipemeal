package com.swipemeal.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private Category category;

    @ManyToOne
    private DietType diet;

    private Integer maxPrepTime; // en minutos

    @ManyToMany
    @JoinTable(
            name = "user_available_ingredients",
            joinColumns = @JoinColumn(name = "preference_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> availableIngredients;
}

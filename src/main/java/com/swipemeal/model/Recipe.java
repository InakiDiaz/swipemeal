package com.swipemeal.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sourceId;

    @Column(nullable = false)
    private String title;

    private String imageUrl;

    @Column(nullable = false)
    private Integer prepTime; // en minutos

    @ManyToOne
    private Category category;

    @ManyToOne
    private DietType dietType;

    @ManyToMany
    @JoinTable(
            name = "recipe_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;
}

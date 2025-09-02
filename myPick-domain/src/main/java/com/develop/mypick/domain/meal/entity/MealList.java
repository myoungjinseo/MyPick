package com.develop.mypick.domain.meal.entity;

import com.develop.mypick.common.entity.BaseTimeEntity;
import com.develop.mypick.domain.nutritionFact.entity.NutritionFact;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MealList extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private Meal meal;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "favorite_id")
    private Favorite favorite;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nutrition_fact_id", nullable = false)
    private NutritionFact nutritionFact;

    @NotNull
    @Column(name = "gram", nullable = false)
    private double gram;
    @Builder
    private MealList(Long id, NutritionFact nutritionFact, double gram, Favorite favorite, Meal meal) {
        this.id = id;
        this.gram = gram;
        this.favorite = favorite;
        this.nutritionFact = nutritionFact;
        this.meal = meal;
    }


    public void updateMealList(double gram, NutritionFact nutritionFact) {
        this.gram = gram;
        this.nutritionFact = nutritionFact;
    }
}
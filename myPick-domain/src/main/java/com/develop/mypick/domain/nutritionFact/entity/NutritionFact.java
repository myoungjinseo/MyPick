package com.develop.mypick.domain.nutritionFact.entity;


import com.develop.mypick.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class NutritionFact {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "food_name",nullable = false)
    private String foodName;

    @Column(name = "cal")
    private double cal;

    @Column(name = "protein")
    private double protein;

    @Column(name = "fat")
    private double fat;

    @Column(name = "carbs")
    private double carbs;

    @Column(name = "vitaminC")
    private double vitaminC;

    @Column(name = "vitaminB12")
    private double vitaminB12;

    @Column(name = "vitaminE")
    private double vitaminE;

    @Column(name = "sodium")
    private double sodium;

    @Column(name = "potassium")
    private double potassium;

    @Column(name = "fiber")
    private double fiber;

    @Column(name = "sugar")
    private double sugar;

    @Column(name = "calcium")
    private double calcium;


    @Builder
    public NutritionFact(Long id, String foodName, double cal, double protein, double fat, double carbs, double vitaminC, double vitaminB12, double vitaminE, double sodium, double potassium, double fiber, double sugar, double calcium) {
        this.id = id;
        this.foodName = foodName;
        this.cal = cal;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.vitaminC = vitaminC;
        this.vitaminB12 = vitaminB12;
        this.vitaminE = vitaminE;
        this.sodium = sodium;
        this.potassium = potassium;
        this.fiber = fiber;
        this.sugar = sugar;
        this.calcium = calcium;
    }
}
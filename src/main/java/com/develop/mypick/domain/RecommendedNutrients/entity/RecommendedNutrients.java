package com.develop.mypick.domain.RecommendedNutrients.entity;

import com.develop.mypick.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecommendedNutrients extends BaseTimeEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cal", nullable = false)
    private double cal;

    @Column(name = "protein", nullable = false)
    private double protein;

    @Column(name = "fat", nullable = false)
    private double fat;

    @Column(name = "carbs", nullable = false)
    private double carbs;

    @Column(name = "vitaminC", nullable = false)
    private double vitaminC;

    @Column(name = "vitaminB12", nullable = false)
    private double vitaminB12;

    @Column(name = "vitaminE", nullable = false)
    private double vitaminE;

    @Column(name = "sodium", nullable = false)
    private double sodium;

    @Column(name = "potassium", nullable = false)
    private double potassium;

    @Column(name = "fiber", nullable = false)
    private double fiber;

    @Column(name = "sugar", nullable = false)
    private double sugar;

    @Column(name = "calcium", nullable = false)
    private double calcium;

}

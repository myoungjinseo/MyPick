package com.develop.mypick.domain.Recommended.entity;

import com.develop.mypick.common.entity.BaseTimeEntity;
import com.develop.mypick.domain.user.entity.AuthUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Recommended extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AuthUser authUser;

    @Builder
    public Recommended(Long id, double cal, double protein, double fat, double carbs, double vitaminC, double vitaminB12, double vitaminE, double sodium, double potassium, double fiber, double sugar, double calcium,AuthUser user) {
        this.id = id;
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
        this.authUser = user;
    }

    public void updateRecommended(Recommended recommended){
        this.cal = recommended.getCal();
        this.protein = recommended.getProtein();
        this.fat = recommended.getFat();
        this.carbs = recommended.getCarbs();
        this.vitaminC = recommended.getVitaminC();
        this.vitaminB12 = recommended.getVitaminB12();
        this.vitaminE = recommended.getVitaminE();
        this.sodium = recommended.getSodium();
        this.potassium = recommended.getPotassium();
        this.fiber = recommended.getFiber();
        this.sugar = recommended.getSugar();
        this.calcium = recommended.getCalcium();
    }
}

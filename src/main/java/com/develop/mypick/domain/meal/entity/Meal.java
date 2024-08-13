package com.develop.mypick.domain.meal.entity;

import com.develop.mypick.common.entity.BaseTimeEntity;
import com.develop.mypick.domain.user.entity.AuthUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Meal extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "meal_date", nullable = false)
    private LocalDate mealDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AuthUser authUser;

    @Builder
    public Meal(Long id, LocalDate mealDate, AuthUser authUser) {
        this.id = id;
        this.authUser = authUser;
        this.mealDate = mealDate;
    }
}

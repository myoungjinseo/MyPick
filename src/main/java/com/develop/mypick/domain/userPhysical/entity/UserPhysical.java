package com.develop.mypick.domain.userPhysical.entity;


import com.develop.mypick.common.entity.BaseTimeEntity;

import com.develop.mypick.domain.user.entity.User;
import com.develop.mypick.domain.userPhysical.enums.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserPhysical extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "height", nullable = false)
    private float height;

    @Column(name = "weight", nullable = false)
    private float weight;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "activity_coefficient", nullable = false)
    private int activityCoefficient;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public UserPhysical(Long id, Gender gender, float height, float weight, int age, int activityCoefficient, User user) {
        this.id = id;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.activityCoefficient = activityCoefficient;
        this.user = user;
    }

 }
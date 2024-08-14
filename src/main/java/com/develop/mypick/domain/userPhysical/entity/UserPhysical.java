package com.develop.mypick.domain.userPhysical.entity;


import com.develop.mypick.common.entity.BaseTimeEntity;

import com.develop.mypick.domain.user.entity.AuthUser;
import com.develop.mypick.domain.userPhysical.enums.ChronicDisease;
import com.develop.mypick.domain.userPhysical.enums.Gender;
import com.develop.mypick.domain.userPhysical.enums.Goal;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    @Enumerated(value = EnumType.STRING)
    @Column(name = "goal", nullable = false)
    private Goal goal;

    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    @Column(name = "chronic_diseases")
    private Set<ChronicDisease> chronicDiseases;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AuthUser authUser;

    @Builder
    public UserPhysical(Long id, Gender gender, float height, float weight, int age, Goal goal, Set<ChronicDisease> chronicDiseases, AuthUser authUser) {
        this.id = id;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.goal = goal;
        this.chronicDiseases = chronicDiseases;
        this.authUser = authUser;
    }

 }
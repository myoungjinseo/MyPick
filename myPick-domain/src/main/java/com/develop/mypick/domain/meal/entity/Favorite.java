package com.develop.mypick.domain.meal.entity;

import com.develop.mypick.common.entity.BaseTimeEntity;
import com.develop.mypick.domain.user.entity.AuthUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Favorite extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", length = 15)
    private String title;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AuthUser authUser;

    @Builder
    public Favorite(Long id, String title,  AuthUser authUser) {
        this.id = id;
        this.title = title;
        this.authUser = authUser;
    }


}
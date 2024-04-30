package com.develop.mypick.domain.foodRegistration.entity;

import com.develop.mypick.common.entity.BaseTimeEntity;
import com.develop.mypick.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FoodRegistration extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(max = 255)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Size(max = 256)
    @Column(name = "request_content", length = 256)
    private String requestContent;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public FoodRegistration(Long id, String name, String requestContent, User user) {
        this.id = id;
        this.name = name;
        this.requestContent = requestContent;
        this.user = user;
    }
}
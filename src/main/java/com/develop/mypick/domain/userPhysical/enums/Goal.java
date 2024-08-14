package com.develop.mypick.domain.userPhysical.enums;

import lombok.Getter;

@Getter
public enum Goal {
    DIET("다이어트"),
    WEIGHT("근육"),
    HEALTH("건강");

    private final String goal;

    Goal(String goal) {
        this.goal = goal;
    }
}

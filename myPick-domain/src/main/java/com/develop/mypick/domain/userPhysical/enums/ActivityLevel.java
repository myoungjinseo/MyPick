package com.develop.mypick.domain.userPhysical.enums;

import lombok.Getter;

@Getter
public enum ActivityLevel {

    SEDENTARY("아주 낮음", 1.2f),
    LIGHTLY("낮음", 1.375f),
    MODERATELY("보통", 1.55f),
    VERY("활동적", 1.725f),
    EXTRA("매우 활동적", 1.9f);

    private final String activityLevel;
    private final float value;

    ActivityLevel(String activityLevel, float value) {
        this.activityLevel = activityLevel;
        this.value = value;
    }
}
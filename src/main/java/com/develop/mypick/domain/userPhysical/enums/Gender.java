package com.develop.mypick.domain.userPhysical.enums;

import lombok.Getter;

@Getter
public enum Gender {
    FEMALE("여성"), MALE("남성");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}

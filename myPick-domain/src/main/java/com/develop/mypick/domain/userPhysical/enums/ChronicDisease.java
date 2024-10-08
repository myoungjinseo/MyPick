package com.develop.mypick.domain.userPhysical.enums;


public enum ChronicDisease {
    HYPERTENSION("고혈압"),
    HIGH_CHOLESTEROL("고지혈증"),
    DIABETES("당뇨"),
    HEART_DISEASE("심장병"),
    OSTEOPOROSIS("골다공증"),
    KIDNEY_DISEASE("신장 질환"),
    STROKE("뇌졸증");

    private final String value;

    public String getValue() {
        return value;
    }

    ChronicDisease(String value) {
        this.value = value;
    }
}

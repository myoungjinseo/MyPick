package com.develop.mypick.api.user.dto.request;

import com.develop.mypick.domain.userPhysical.enums.ChronicDisease;
import com.develop.mypick.domain.userPhysical.enums.Gender;
import com.develop.mypick.domain.userPhysical.enums.Goal;

import java.util.Set;

public record UserRequest(
        String email,
        String username,
        String password,
        int age,
        Gender gender,
        float weight,
        float height,
        Goal goal,
        Set<ChronicDisease> chronicDiseases

) {

}

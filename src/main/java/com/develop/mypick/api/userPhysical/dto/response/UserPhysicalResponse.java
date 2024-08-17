package com.develop.mypick.api.userPhysical.dto.response;

import com.develop.mypick.domain.userPhysical.entity.UserPhysical;
import com.develop.mypick.domain.userPhysical.enums.ChronicDisease;
import com.develop.mypick.domain.userPhysical.enums.Gender;
import com.develop.mypick.domain.userPhysical.enums.Goal;

import java.util.Set;

public record UserPhysicalResponse(Gender gender,
                                   float height,
                                   float weight,
                                   int age,
                                   Goal goal,
                                   Set<ChronicDisease> chronicDiseases) {

    public static UserPhysicalResponse of(UserPhysical userPhysical){
        return new UserPhysicalResponse(userPhysical.getGender(), userPhysical.getHeight(), userPhysical.getWeight(), userPhysical.getAge(), userPhysical.getGoal(),userPhysical.getChronicDiseases());
    }
}

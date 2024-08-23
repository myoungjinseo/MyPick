package com.develop.mypick.api.Recommended.dto.response;

import com.develop.mypick.domain.Recommended.entity.Recommended;
import com.develop.mypick.domain.user.entity.AuthUser;

public record ChatGptResponse(
        Long recommendedId,
        Long userId,
        RecommendedResponse response
) {

    public static ChatGptResponse of(AuthUser user, Recommended recommended,RecommendedResponse response){
        return new ChatGptResponse(recommended.getId(), user.getId(), response);
    }
}

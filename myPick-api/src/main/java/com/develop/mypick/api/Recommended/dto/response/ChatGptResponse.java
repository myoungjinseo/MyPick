package com.develop.mypick.api.Recommended.dto.response;

import com.develop.mypick.domain.Recommended.entity.Recommended;
import com.develop.mypick.domain.user.entity.AuthUser;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ChatGptResponse", description = "ChatGPT 응답 dto")
public record ChatGptResponse(
        @Schema(description = "추천 id", example = "1")
        Long recommendedId,
        @Schema(description = "유저 id", example = "1")
        Long userId,
        @Schema(description = "chatGPT 추천 결과")
        RecommendedResponse response
) {

    public static ChatGptResponse of(AuthUser user, Recommended recommended, RecommendedResponse response){
        return new ChatGptResponse(recommended.getId(), user.getId(), response);
    }
}

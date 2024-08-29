package com.develop.mypick.api.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TokenResponse",description = "로그이 응답 dto")
public record TokenResponse(
        @Schema(description = "AccessToken", example = "AccessToken")
        String accessToken,
        @Schema(description = "RefreshToken", example = "RefreshToken")
        String refreshToken) {

}

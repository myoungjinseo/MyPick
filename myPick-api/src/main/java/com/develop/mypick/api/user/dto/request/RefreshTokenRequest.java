package com.develop.mypick.api.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "RefreshTokenRequest", description = "리프레쉬 토큰 요청 dto")
public record RefreshTokenRequest(
        @Schema(description = "리프레쉬 토큰", example = "refreshToken")
        String refreshToken
) {
}

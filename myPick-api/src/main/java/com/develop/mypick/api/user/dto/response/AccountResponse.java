package com.develop.mypick.api.user.dto.response;

import com.develop.mypick.domain.user.entity.AuthUser;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AccountResponse",description = "회원가입 응답 dto")
public record AccountResponse(
        @Schema(description = "유저 id", example = "1")
        Long userId ,
        @Schema(description = "유저 email", example = "smjsih@naver.com")
        String email
) {
    public static AccountResponse of(AuthUser authUser){
        return new AccountResponse(authUser.getId(), authUser.getEmail());
    }
}
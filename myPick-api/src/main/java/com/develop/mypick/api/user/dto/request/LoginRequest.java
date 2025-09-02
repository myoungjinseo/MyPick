package com.develop.mypick.api.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
@Schema(name = "LoginRequest",description = "로그인 요청 dto")
public record LoginRequest(
        @NotNull(message = "이메일을 입력해 주세요.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        @Schema(description = "이메일", example = "smjsih@naver.com")
        String email,
        @NotNull(message = "비밀번호를 입력해 주세요.")
        @Schema(description = "비밀번호", example = "@test1234")
        String password
) {

}

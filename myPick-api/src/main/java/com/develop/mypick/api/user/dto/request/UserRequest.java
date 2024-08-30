package com.develop.mypick.api.user.dto.request;


import com.develop.mypick.domain.userPhysical.enums.ActivityLevel;
import com.develop.mypick.domain.userPhysical.enums.ChronicDisease;
import com.develop.mypick.domain.userPhysical.enums.Gender;
import com.develop.mypick.domain.userPhysical.enums.Goal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Set;

@Schema(name = "UserRequest",description = "회원가입 요청 dto")
public record UserRequest(
        @NotNull(message = "이메일: 필수 정보입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        @Schema(description = "이메일", example = "smjsih@naver.com")
        String email,
        @NotNull(message = "이름: 필수 정보입니다.")
        @Schema(description = "이름", example = "서명진")
        String username,
        @NotNull(message = "비밀번호: 필수 정보입니다.")
        @Schema(description = "비밀번호", example = "@test1234")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        String password,
        @NotNull(message = "나이: 필수 정보입니다.")
        @Schema(description = "나이", example = "26")
        int age,
        @NotNull(message = "성별: 필수 정보입니다.")
        @Schema(description = "성별", example = "MALE")
        Gender gender,
        @NotNull(message = "몸무게: 필수 정보입니다.")
        @Schema(description = "몸무게", example = "65")
        float weight,
        @NotNull(message = "키: 필수 정보입니다.")
        @Schema(description = "키", example = "170")
        float height,
        @NotNull(message = "목적: 필수 정보입니다.")
        @Schema(description = "목적", example = "DIET")
        Goal goal,
        @Schema(description = "지병",example = "null")
        Set<ChronicDisease> chronicDiseases,
        @Schema(description = "활동지수", example = "MODERATELY")
        ActivityLevel activityLevel

) {

}

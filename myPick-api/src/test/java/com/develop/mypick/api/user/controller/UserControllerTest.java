package com.develop.mypick.api.user.controller;

import com.develop.mypick.api.user.dto.request.LoginRequest;
import com.develop.mypick.api.user.dto.request.RefreshTokenRequest;
import com.develop.mypick.api.user.dto.request.UserRequest;
import com.develop.mypick.api.user.dto.response.AccountResponse;
import com.develop.mypick.api.user.dto.response.TokenResponse;
import com.develop.mypick.api.user.service.UserService;
import com.develop.mypick.common.exception.ErrorCode;
import com.develop.mypick.common.exception.ErrorException;
import com.develop.mypick.domain.userPhysical.enums.ActivityLevel;
import com.develop.mypick.domain.userPhysical.enums.Gender;
import com.develop.mypick.domain.userPhysical.enums.Goal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;
    @Nested
    class 회원가입할_때 {
        @Test
        void 이메일_중복() throws Exception {
            // Given
            UserRequest request = new UserRequest(
                    "test@naver.com",
                    null,
                    "@test1234",
                    26,
                    Gender.MALE,
                    65,
                    170,
                    Goal.DIET,
                    null,
                    ActivityLevel.MODERATELY);

            // When & Then
            mockMvc.perform(
                            post("/api/user/signUp")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))

            )
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorCode").value("BAD_REQUEST"))
                    .andExpect(jsonPath("$.reason").value("이름: 필수 정보입니다."))
                    .andDo(print());
        }

        @Test
        void 정상_작동() throws Exception {
            // Given
            UserRequest request = new UserRequest(
                    "test@naver.com",
                    "홍길동",
                    "@test1234",
                    26,
                    Gender.MALE,
                    65,
                    170,
                    Goal.DIET,
                    null,
                    ActivityLevel.MODERATELY);

            given(userService.signUp(any()))
                    .willReturn(new AccountResponse(
                            1L,
                            "test@naver.com"
                    ));

            // When & Then
            mockMvc.perform(
                            post("/api/user/signUp")
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(request))
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.userId").value(1))
                    .andExpect(jsonPath("$.email").value("test@naver.com"))
                    .andDo(print());
        }
    }

    @Nested
    class 로그인할_때 {
        @Test
        void 이메일_형식이_아닐_때() throws Exception {
            // Given
            LoginRequest request = new LoginRequest(
                    "testnaver.com",
                    "@test1234"
            );

            // When & Then
            mockMvc.perform(
                            post("/api/user/signIn")
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(request))
                    )
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorCode").value("BAD_REQUEST"))
                    .andExpect(jsonPath("$.reason").value("이메일 형식이 올바르지 않습니다."))
                    .andDo(print());
        }

        @Test
        void 이메일_null일_떄() throws Exception {
            // Given
            LoginRequest request = new LoginRequest(
                    null,
                    "@test1234"
            );


            // When & Then
            mockMvc.perform(
                            post("/api/user/signIn")
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(request))
                                    .with(csrf())
                    )
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorCode").value("BAD_REQUEST"))
                    .andExpect(jsonPath("$.reason").value("이메일을 입력해 주세요."))
                    .andDo(print());
        }

        @Test
        void 비밀번호_틀렸을_떄() throws Exception {
            // Given
            LoginRequest request = new LoginRequest(
                    "test@naver.com",
                    null
            );

            given(userService.signIn(any()))
                    .willThrow(new ErrorException(ErrorCode.LOGIN_FAIL));

            // When & Then
            mockMvc.perform(
                            post("/api/user/signIn")
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(request))
                    )
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errorCode").value("BAD_REQUEST"))
                    .andExpect(jsonPath("$.reason").value("비밀번호를 입력해 주세요."))
                    .andDo(print());
        }
        @Test
        void 정상_작동() throws Exception {
            // Given
            LoginRequest request = new LoginRequest(
                    "test@naver.com",
                    "@test1234"
            );

            given(userService.signIn(any()))
                    .willReturn(TokenResponse.of("AccessToken","RefreshToken"));

            // When & Then
            mockMvc.perform(
                            post("/api/user/signIn")
                                    .accept(MediaType.APPLICATION_JSON)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(request))
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.accessToken").exists())
                    .andExpect(jsonPath("$.refreshToken").exists())
                    .andDo(print());
        }
    }


    @Test
    void 리프레시_토큰() throws Exception {
        // Given
        RefreshTokenRequest request = new RefreshTokenRequest("RefreshToken");
        given(userService.reissueToken(any()))
                .willReturn(TokenResponse.of("NewAccessToken","NewRefreshToken"));

        // When & Then
        mockMvc.perform(
                get("/api/user/reissue")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.refreshToken").exists())
                .andDo(print());
    }
}
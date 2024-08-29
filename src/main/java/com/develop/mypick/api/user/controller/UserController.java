package com.develop.mypick.api.user.controller;

import com.develop.mypick.api.user.dto.request.LoginRequest;
import com.develop.mypick.api.user.dto.request.UserRequest;
import com.develop.mypick.api.user.dto.response.AccountResponse;
import com.develop.mypick.api.user.dto.response.TokenResponse;
import com.develop.mypick.api.user.service.UserService;
import com.develop.mypick.domain.user.entity.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Tag(name = "1. [유저]", description = "유저관련 API입니다.")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "회원가입을 합니다.")
    @PostMapping("/signUp")
    public ResponseEntity<AccountResponse> signUp(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.signUp(userRequest));

    }

    @Operation(summary = "로그인", description = "로그인 시 토큰을 줍니다.")
    @PostMapping("/signIn")
    public ResponseEntity<TokenResponse> signIn(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.signIn(loginRequest));
    }

    @GetMapping("/test")
    public ResponseEntity<Long> test(@AuthenticationPrincipal AuthUser user) {
//        String username = principal.getName().split(":")[0];
        return ResponseEntity.ok().body(user.getId());
    }
}
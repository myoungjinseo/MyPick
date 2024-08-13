package com.develop.mypick.api.user.controller;

import com.develop.mypick.api.user.dto.request.UserRequest;
import com.develop.mypick.api.user.dto.response.AccountResponse;
import com.develop.mypick.api.user.dto.response.TokenResponse;
import com.develop.mypick.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public AccountResponse signUp(@RequestBody UserRequest userRequest) {
        AccountResponse response = userService.signUp(userRequest);
        return response;
    }

    @PostMapping("/signIn")
    public TokenResponse signIn(@RequestBody UserRequest userRequest) {
        TokenResponse response = userService.signIn(userRequest);
        return response;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(Principal principal){
        String username = principal.getName().split(":")[0];
        return ResponseEntity.ok().body(username);
    }
}
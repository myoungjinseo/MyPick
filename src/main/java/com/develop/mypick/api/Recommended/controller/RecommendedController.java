package com.develop.mypick.api.Recommended.controller;

import com.develop.mypick.api.Recommended.dto.response.ChatGptResponse;
import com.develop.mypick.api.Recommended.service.ChatGptService;
import com.develop.mypick.domain.user.entity.AuthUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "2. [식단 추천]", description = "식단 추천 API입니다.")
@RestController
@RequestMapping("/api/recommend")
@RequiredArgsConstructor
public class RecommendedController {

    private final ChatGptService chatGptService;

    @Operation(summary = "식단 추천", description = "ChatGPT를 이용해서 식단 추천을 합니다.")
    @GetMapping("/")
    public ResponseEntity<ChatGptResponse> getRecommend(@AuthenticationPrincipal AuthUser user) throws JsonProcessingException {
        return ResponseEntity.ok(chatGptService.sendChatGpt(user));
    }
}

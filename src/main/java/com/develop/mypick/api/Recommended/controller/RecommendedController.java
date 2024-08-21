package com.develop.mypick.api.Recommended.controller;

import com.develop.mypick.api.Recommended.dto.response.ChatGptResponse;
import com.develop.mypick.api.Recommended.service.ChatGptService;
import com.develop.mypick.domain.user.entity.AuthUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommend")
@RequiredArgsConstructor
public class RecommendedController {

    private final ChatGptService chatGptService;

    @GetMapping("/")
    public ChatGptResponse getRecommend(@AuthenticationPrincipal AuthUser user) throws JsonProcessingException {
        ChatGptResponse response = chatGptService.sendChatGpt(user);
        return response;
    }
}

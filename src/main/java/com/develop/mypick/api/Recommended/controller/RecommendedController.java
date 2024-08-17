package com.develop.mypick.api.Recommended.controller;

import com.develop.mypick.api.Recommended.dto.response.RecommendedResponse;
import com.develop.mypick.api.Recommended.service.RecommendedService;
import com.develop.mypick.domain.user.entity.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommend")
@RequiredArgsConstructor
public class RecommendedController {

    private final RecommendedService recommendedService;

    @GetMapping("/")
    public RecommendedResponse getRecommend(@AuthenticationPrincipal AuthUser user) {
        RecommendedResponse response = recommendedService.createRecommend(user);
        return response;
    }
}

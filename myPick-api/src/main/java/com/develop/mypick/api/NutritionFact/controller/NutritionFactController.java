package com.develop.mypick.api.NutritionFact.controller;

import com.develop.mypick.api.NutritionFact.service.NutritionFactService;
import com.develop.mypick.common.dto.slice.SliceResponse;
import com.develop.mypick.domain.user.entity.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "2. [음식]", description = "음식 API입니다.")
@RestController
@RequestMapping("/api/nutritionFacts")
@RequiredArgsConstructor
public class NutritionFactController {

    private final NutritionFactService nutritionFactService;

    @Operation(summary = "음식 검색", description = "DB에 있는 음식 데이터를 가져옵니다.")
    @GetMapping("/nutritionFact")
    public ResponseEntity<SliceResponse> getNutritionFact(@AuthenticationPrincipal AuthUser user, Pageable pageable, @RequestParam String foodName) {
        SliceResponse response = nutritionFactService.findByFoodName(foodName,pageable);
        return ResponseEntity.ok(response) ;
    }
}

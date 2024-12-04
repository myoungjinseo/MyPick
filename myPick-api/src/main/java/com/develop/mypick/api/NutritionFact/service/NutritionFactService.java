package com.develop.mypick.api.NutritionFact.service;

import com.develop.mypick.common.dto.slice.SliceResponse;
import com.develop.mypick.domain.nutritionFact.repo.NutritionFactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NutritionFactService {

    private final NutritionFactRepository nutritionFactRepository;

    public SliceResponse findByFoodName(String foodName, Pageable pageable) {
        Slice<String> foodNameSlice = nutritionFactRepository.findByFoodName(foodName, pageable);
        return SliceResponse.from(foodNameSlice);
    }
}

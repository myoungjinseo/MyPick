package com.develop.mypick.domain.nutritionFact.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface NutritionFactRepositoryCustom {
    Slice<String> findByFoodName(String foodName, Pageable pageable);

}

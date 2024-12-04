package com.develop.mypick.domain.nutritionFact.repo;

import com.develop.mypick.domain.nutritionFact.entity.NutritionFact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionFactRepository extends JpaRepository<NutritionFact,Long>, NutritionFactRepositoryCustom {
    long count();
}

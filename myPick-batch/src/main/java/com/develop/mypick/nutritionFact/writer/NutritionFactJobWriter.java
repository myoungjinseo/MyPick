package com.develop.mypick.nutritionFact.writer;

import com.develop.mypick.nutritionFact.dto.NutritionFactResponse;
import com.develop.mypick.nutritionFact.service.NutritionFactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class NutritionFactJobWriter implements ItemWriter<List<NutritionFactResponse>> {

    private final NutritionFactService nutritionFactService;
    @Override
    @StepScope
    public void write(Chunk<? extends List<NutritionFactResponse>> items) {
        List<? extends List<NutritionFactResponse>> item = items.getItems();
        item.stream()
                .filter(getNutritionFact -> getNutritionFact != null)
                .forEach(getNutritionFact -> nutritionFactService.createNutrition(getNutritionFact));
    }
}

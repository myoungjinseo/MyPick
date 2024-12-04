package com.develop.mypick.nutritionFact.reader;

import com.develop.mypick.nutritionFact.dto.NutritionFactResponse;
import com.develop.mypick.nutritionFact.service.NutritionFactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class NutritionFactReader implements ItemReader<List<NutritionFactResponse>> {

    private final NutritionFactService nutritionFactService;

    private int page = 1;
    @Override
    @StepScope
    public List<NutritionFactResponse> read() {
        int firstPage = nutritionFactService.page();
        int maxPage = nutritionFactService.maxPage();

        if (page < firstPage){
            page = firstPage;
        }

        if (page >= maxPage){
            log.info("모든 값을 받았습니다.");
            return null;
        }

        List<NutritionFactResponse> nutritionDto = nutritionFactService.getListNutrition(page);
        page ++;
        return nutritionDto;
    }
}

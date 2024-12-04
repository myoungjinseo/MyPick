package com.develop.mypick.domain.nutritionFact.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.develop.mypick.domain.nutritionFact.entity.QNutritionFact.nutritionFact;

@Repository
@RequiredArgsConstructor
public class NutritionFactRepositoryCustomImpl implements NutritionFactRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<String> findByFoodName(String foodName, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        List<String> nutritionFacts = jpaQueryFactory
                .select(nutritionFact.foodName)
                .from(nutritionFact)
                .where(nutritionFact.foodName.contains(foodName))
                .offset(pageable.getOffset())
                .limit(pageSize - 1)
                .fetch();
        boolean hasNext = false;
        if (nutritionFacts.size() > pageSize) {
            nutritionFacts.remove(pageSize);
            hasNext = true;
        }

        return new SliceImpl<>(nutritionFacts, pageable, hasNext);
    }

}

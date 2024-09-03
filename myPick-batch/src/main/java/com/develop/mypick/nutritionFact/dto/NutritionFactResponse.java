package com.develop.mypick.nutritionFact.dto;

import com.develop.mypick.domain.nutritionFact.entity.NutritionFact;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NutritionFactResponse(
        @JsonProperty("NUM")
        Long id,
        @JsonProperty("FOOD_NM_KR")
        String foodName,
        @JsonProperty("AMT_NUM1")
        @JsonDeserialize(using = CommaToDoubleDeserializer.class)
        double cal,
        @JsonProperty("AMT_NUM3")
        @JsonDeserialize(using = CommaToDoubleDeserializer.class)
        double protein,
        @JsonProperty("AMT_NUM4")
        @JsonDeserialize(using = CommaToDoubleDeserializer.class)
        double fat,
        @JsonProperty("AMT_NUM7")
        @JsonDeserialize(using = CommaToDoubleDeserializer.class)
        double carbs,
        @JsonProperty("AMT_NUM22")
        @JsonDeserialize(using = CommaToDoubleDeserializer.class)
        double vitaminC,
        @JsonProperty("AMT_NUM31")
        @JsonDeserialize(using = CommaToDoubleDeserializer.class)
        double vitaminB12,
        @JsonProperty("AMT_NUM38")
        @JsonDeserialize(using = CommaToDoubleDeserializer.class)
        double vitaminE,
        @JsonProperty("AMT_NUM14")
        @JsonDeserialize(using = CommaToDoubleDeserializer.class)
        double sodium,
        @JsonProperty("AMT_NUM13")
        @JsonDeserialize(using = CommaToDoubleDeserializer.class)
        double potassium,
        @JsonProperty("AMT_NUM9")
        @JsonDeserialize(using = CommaToDoubleDeserializer.class)
        double fiber,
        @JsonProperty("AMT_NUM8")
        @JsonDeserialize(using = CommaToDoubleDeserializer.class)
        double sugar,
        @JsonProperty("AMT_NUM10")
        @JsonDeserialize(using = CommaToDoubleDeserializer.class)
        double calcium

) {
        public static NutritionFact toEntity(NutritionFactResponse response){
                return NutritionFact.builder()
                        .id(response.id())
                        .foodName(response.foodName())
                        .cal(response.cal())
                        .calcium(response.calcium())
                        .carbs(response.carbs())
                        .fat(response.fat())
                        .fiber(response.fiber())
                        .sugar(response.sugar())
                        .potassium(response.potassium())
                        .protein(response.protein())
                        .sodium(response.sodium())
                        .vitaminB12(response.vitaminB12())
                        .vitaminC(response.vitaminC())
                        .vitaminE(response.vitaminE())
                        .build();
        }
        /*
         * 리스트에서 받은 dto들을 toEntity 반환해서 NutritionFact값으로 변경
         * NutritionFact 값들을 Collectors.toList를 통해 List<NutritionFact>로 변경
         * */
        public static List<NutritionFact> toListEntity(List<NutritionFactResponse> list) {
                return list.stream()
                        .filter(dto -> dto.foodName() != null)
                        .map(NutritionFactResponse::toEntity)
                        .collect(Collectors.toList());
        }
}

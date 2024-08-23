package com.develop.mypick.api.Recommended.dto.response;


import com.develop.mypick.domain.Recommended.entity.Recommended;
import com.develop.mypick.domain.user.entity.AuthUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecommendedResponse(

        @JsonProperty("칼로리 ")
        double cal,
        @JsonProperty("단백질 ")
        double protein,
        @JsonProperty("지방 ")
        double fat,
        @JsonProperty("탄수화물 ")
        double carbs,
        @JsonProperty("비타민C ")
        double vitaminC,
        @JsonProperty("비타민B12 ")
        double vitaminB12,
        @JsonProperty("비타민E ")
        double vitaminE,
        @JsonProperty("나트륨 ")
        double sodium,
        @JsonProperty("칼륨 ")
        double potassium,
        @JsonProperty("섬유질 ")
        double fiber,
        @JsonProperty("당류 ")
        double sugar,
        @JsonProperty("칼슘 ")
        double calcium

) {
        public static Recommended toEntity(AuthUser user, RecommendedResponse response){
                return Recommended.builder()
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
                        .user(user)
                        .build();
        }
}

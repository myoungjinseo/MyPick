package com.develop.mypick.api.Recommended.dto.response;


import com.develop.mypick.domain.Recommended.entity.Recommended;
import com.develop.mypick.domain.user.entity.AuthUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "RecommendedResponse", description = "ChatGPT 식단 추천 응답 dto")
@JsonIgnoreProperties(ignoreUnknown = true)
public record RecommendedResponse(

        @Schema(description = "칼로리", example = "2400.0")
        @JsonProperty("칼로리 ")
        double cal,
        @Schema(description = "단백질", example = "95.0")
        @JsonProperty("단백질 ")
        double protein,
        @Schema(description = "지방", example = "66.0")
        @JsonProperty("지방 ")
        double fat,
        @Schema(description = "탄수화물", example = "295.0")
        @JsonProperty("탄수화물 ")
        double carbs,
        @Schema(description = "비타민C", example = "90.0")
        @JsonProperty("비타민C ")
        double vitaminC,
        @Schema(description = "비타민B12", example = "2.4")
        @JsonProperty("비타민B12 ")
        double vitaminB12,
        @Schema(description = "비타민E", example = "15.0")
        @JsonProperty("비타민E ")
        double vitaminE,
        @Schema(description = "나트륨", example = "2300.0")
        @JsonProperty("나트륨 ")
        double sodium,
        @Schema(description = "칼륨", example = "3500.0")
        @JsonProperty("칼륨 ")
        double potassium,
        @Schema(description = "섬유질", example = "30.0")
        @JsonProperty("섬유질 ")
        double fiber,
        @Schema(description = "당류", example = "90.0")
        @JsonProperty("당류 ")
        double sugar,
        @Schema(description = "칼슘", example = "1000.0")
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

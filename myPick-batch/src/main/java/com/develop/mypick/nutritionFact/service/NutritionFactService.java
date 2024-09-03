package com.develop.mypick.nutritionFact.service;

import com.develop.mypick.common.exception.ErrorCode;
import com.develop.mypick.common.exception.ErrorException;
import com.develop.mypick.domain.nutritionFact.entity.NutritionFact;
import com.develop.mypick.domain.nutritionFact.repo.NutritionFactRepository;
import com.develop.mypick.nutritionFact.dto.NutritionFactResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NutritionFactService {

    @Value("${nutrition.service-key}")
    private String serviceKey;

    private final NutritionFactRepository nutritionFactRepository;

    private final int NUM_OF_ROWS = 100;



    /*
     * getNutrition 메소드를 통해 jsonString 값 받아오기
     * getListNutritionDto를 통해  List<NutritionFactResponse> 형변환
     * toListEntity를 통해 dto 리스트값들 entity값으로 변환
     * 변환된 엔티티를 저장하는 메소드
     * */
    public List<NutritionFactResponse> getListNutrition(int page){
        String response = getNutrition(page);
        List<NutritionFactResponse> responseList = getListNutritionDto(response);
        if( responseList == null || responseList.isEmpty() ){
            throw new ErrorException(ErrorCode.NON_EXISTENT_LIST_DTO);
        }
        return responseList;
    }

    /*
     * url에 있는 값 String 형태로 받아오는 메소드
     * */
    public String getNutrition(int pageNo){
        RestTemplate restTemplate = new RestTemplate();

        RestClient restClient = RestClient.create(restTemplate);

        return  restClient.get()
                .uri("https://apis.data.go.kr/1471000/FoodNtrCpntDbInfo01/getFoodNtrCpntDbInq01?serviceKey={serviceKey}&pageNo={pageNo}&numOfRows={numOfRows}&type=json" ,serviceKey , pageNo,NUM_OF_ROWS)
                .retrieve()
                .body(String.class);

    }

    /*
    * 음식별 건강 정보를 저장하는 메소드
    * */
    @Transactional
    public void createNutrition(List<NutritionFactResponse> responseList){
        List<NutritionFact> nutritionFacts = NutritionFactResponse.toListEntity(responseList);
        nutritionFactRepository.saveAll(nutritionFacts);
    }

    /*
    * 현재 Page를 찾는 메소드
    * */
    public int page(){
        long count = nutritionFactRepository.count();
        if (count % NUM_OF_ROWS == 0){
            count -= 1;
        }
        int page = Math.toIntExact(count / NUM_OF_ROWS);
        return page;
    }

    /*
    * 최대 페이지를 찾는 메소드
    * */
    public int maxPage(){
        ObjectMapper objectMapper = new ObjectMapper();
        String response = getNutrition(1);
        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode rows = root.at("/body" );
            int totalCount = rows.get("totalCount").asInt();
            if (totalCount % NUM_OF_ROWS == 0){
                totalCount -= 1;
            }
            return totalCount/NUM_OF_ROWS;
        } catch (JsonProcessingException e) {
            throw new ErrorException(ErrorCode.JSON_PARSING_ERROR);
        }
    }


    /*
     * jsonString 값을 list<NutritionFactResponse> 형 변환 메소드
     * ObjectMapper를 통해 json 코드 안에 row 데이터에 필요한 정보 불러옴
     * */
    public List<NutritionFactResponse> getListNutritionDto(String response) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode rows = root.at("/body/items" );
            String json = objectMapper.writeValueAsString(rows);

            return objectMapper.readValue(json, new TypeReference<List<NutritionFactResponse>>(){});
        } catch (JsonProcessingException e) {
            throw new ErrorException(ErrorCode.JSON_PARSING_ERROR);
        }
    }


}

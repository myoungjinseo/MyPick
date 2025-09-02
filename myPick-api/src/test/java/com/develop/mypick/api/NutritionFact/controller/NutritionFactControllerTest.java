package com.develop.mypick.api.NutritionFact.controller;

import com.develop.mypick.api.NutritionFact.service.NutritionFactService;

import com.develop.mypick.common.dto.slice.SliceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NutritionFactController.class)
@AutoConfigureMockMvc(addFilters = false)
class NutritionFactControllerTest {
    @MockBean
    private NutritionFactService nutritionFactService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void 음식_검색() throws Exception {
        Pageable pageable = PageRequest.of(0, 2);
        List<String> foodName = List.of(
                "김치찌개"
        );
        Slice<String> slice = new SliceImpl<>(foodName,pageable,false);
        SliceResponse response = SliceResponse.from(slice);

        given(nutritionFactService.findByFoodName("김치찌개",pageable)).willReturn(response);

        mockMvc.perform(get("/api/nutritionFacts/nutritionFact")
                .param("foodName","김치찌개")
                .param("page","0")
                .param("size", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hasNext").value(false))
                .andExpect(jsonPath("$.data[0]").value("김치찌개"))
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(2));
    }
}
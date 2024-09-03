package com.develop.mypick.nutritionFact.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class CommaToDoubleDeserializer extends JsonDeserializer<Double> {
    @Override
    public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        if (value == null || value.trim().isEmpty()){
            return 0.0;
        }
        // 쉼표 제거 후 double로 변환
        value = value.replace(",", "");
        return Double.parseDouble(value);
    }
}

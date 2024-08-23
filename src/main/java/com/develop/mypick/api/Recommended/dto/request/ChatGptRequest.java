package com.develop.mypick.api.Recommended.dto.request;


import java.util.List;

public record ChatGptRequest(
        String model,
        List<MessageRequest> messages,
        float temperature) {

    public static ChatGptRequest of(String model, List<MessageRequest> messageRequests, float temperature){
        return new ChatGptRequest(model, messageRequests,temperature);
    }
}

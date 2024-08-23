package com.develop.mypick.api.Recommended.dto.request;

public record MessageRequest(
        String role,
        String content
) {
    public static MessageRequest toDto(String role, String content){
        return new MessageRequest(role,content);
    }

}

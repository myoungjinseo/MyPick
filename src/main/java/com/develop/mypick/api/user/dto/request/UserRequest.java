package com.develop.mypick.api.user.dto.request;

public record UserRequest(
        String email,
        String username,
        String password
) {

}

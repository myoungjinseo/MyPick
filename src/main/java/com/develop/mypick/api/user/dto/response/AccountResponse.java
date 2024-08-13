package com.develop.mypick.api.user.dto.response;

import com.develop.mypick.domain.user.entity.AuthUser;

public record AccountResponse(
        Long userId ,
        String email
) {
    public static AccountResponse of(AuthUser authUser){
        return new AccountResponse(authUser.getId(), authUser.getEmail());
    }
}
package com.develop.mypick.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //404 에러가 아닌 400 에러 USER 정보가 없다는 정보를 주지 않기위해
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST,"존재하지 않는 유저 정보입니다"),
    CHATGPT_ERROR(HttpStatus.BAD_REQUEST,"ChatGPT 자체 오류입니다"),
    EXITS_EMAIL(HttpStatus.BAD_REQUEST,"이미 존재하는 이메일입니다"),
    EXITS_USER_PHYSICAL(HttpStatus.BAD_REQUEST,"유저 피지컬이 이미 존재합니다"),
    LOGIN_FAIL(HttpStatus.BAD_REQUEST,"아이디 혹은 비밀번호를 확인하세요.");

    private final HttpStatus httpStatus;
    private final String message;
}
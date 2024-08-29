package com.develop.mypick.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //Auth
    AUTH_NOT_FOUND(HttpStatus.UNAUTHORIZED, "시큐리티 인증 정보를 찾을 수 없습니다."),
    UNKNOWN_ERROR(HttpStatus.UNAUTHORIZED, "알 수 없는 에러"),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 access Token입니다"),
    EXPIRED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 refresh Token입니다. 재로그인하세요"),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "토큰 길이 및 형식이 다른 Token입니다"),
    WRONG_TYPE_TOKEN(HttpStatus.UNAUTHORIZED, "서명이 잘못된 토큰입니다."),
    ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "토큰이 없습니다"),
    TOKEN_SUBJECT_FORMAT_ERROR(HttpStatus.UNAUTHORIZED, "Subject 값에 Long 타입이 아닌 다른 타입이 들어있습니다."),
    AT_EXPIRED_AND_RT_NOT_FOUND(HttpStatus.UNAUTHORIZED, "AT는 만료되었고 RT는 비어있습니다."),
    RT_NOT_FOUND(HttpStatus.UNAUTHORIZED, "RT가 비어있습니다"),


    NOT_FOUND_RECOMMENDED(HttpStatus.NOT_FOUND,"찾을 수 없는 추천 정보입니다."),
    //404 에러가 아닌 400 에러 USER 정보가 없다는 정보를 주지 않기위해
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST,"존재하지 않는 유저 정보입니다"),
    CHATGPT_ERROR(HttpStatus.BAD_REQUEST,"ChatGPT 자체 오류입니다"),
    EXITS_EMAIL(HttpStatus.BAD_REQUEST,"이미 존재하는 이메일입니다."),
    EXITS_USER_PHYSICAL(HttpStatus.BAD_REQUEST,"유저 피지컬이 이미 존재합니다"),
    LOGIN_FAIL(HttpStatus.BAD_REQUEST,"아이디 혹은 비밀번호를 확인하세요.");

    private final HttpStatus httpStatus;
    private final String message;
}
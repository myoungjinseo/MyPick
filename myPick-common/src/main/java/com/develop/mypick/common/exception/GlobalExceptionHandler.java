package com.develop.mypick.common.exception;

import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(ErrorException e) {
        ErrorResponse response = new ErrorResponse(e.getErrorCode().name(), e.getMessage());
        log.error("ErrorException {}", e.getMessage());
        return new ResponseEntity<>(response, e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(final Exception e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage());
        log.error("handleAllException {}", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ErrorResponse> handleAllException(final JsonProcessingException e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(), "json 오류 발생");
        log.error("JsonException {}", e.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }
}
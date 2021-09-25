package com.complexica.weather.config;



import com.complexica.weather.exception.exception.CustomException;
import com.complexica.weather.exception.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity handleApiExceptionHandler(CustomException e) {
        GlobalException globalException = new GlobalException(e.getMessage(), e.getStatus());
        ResponseEntity responseEntity = new ResponseEntity(globalException, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}

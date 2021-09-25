package com.complexica.weather.exception.exception;

public class CustomException extends RuntimeException{
    private Boolean status;
    public CustomException(String message) {
        super(message);
        this.status = false;
    }

    public Boolean getStatus() {
        return status;
    }
}

package com.complexica.weather.exception.exception;

public class GlobalException {
    private Boolean status;
    private String message;

    public GlobalException(String message, Boolean status) {
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
}

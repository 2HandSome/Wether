package com.complexica.weather.persistent.common;

import lombok.Data;

@Data
public class ResponseDTO<T> extends BaseDTO {

    private T data;

    public ResponseDTO() {
    }

    public T getData() {
        return data;
    }

    public ResponseDTO<T> setData(T data) {
        this.data = data;
        return this;
    }

}


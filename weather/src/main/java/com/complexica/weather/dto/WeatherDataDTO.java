package com.complexica.weather.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class WeatherDataDTO implements Serializable {
    private String cityName;
    private String countryCode;
    private Double temperature;
    private Integer clouds;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

}

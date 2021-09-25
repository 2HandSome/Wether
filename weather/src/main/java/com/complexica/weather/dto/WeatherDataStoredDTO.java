package com.complexica.weather.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;

@Data
public class WeatherDataStoredDTO implements Serializable {
    private String cityName;
    private String countryCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time time;

}

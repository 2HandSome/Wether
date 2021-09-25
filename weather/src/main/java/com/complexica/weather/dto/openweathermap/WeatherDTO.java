
package com.complexica.weather.dto.openweathermap;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Data
public class WeatherDTO {
    public Integer id;
    public String main;
    public String description;
    public String icon;
}

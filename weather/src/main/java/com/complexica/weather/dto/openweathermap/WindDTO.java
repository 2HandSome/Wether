
package com.complexica.weather.dto.openweathermap;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Data
public class WindDTO {
    public Double speed;
    public Integer deg;
    public Double gust;
}

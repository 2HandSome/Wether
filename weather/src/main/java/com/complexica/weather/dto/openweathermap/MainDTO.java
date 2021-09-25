
package com.complexica.weather.dto.openweathermap;

import lombok.Data;

@Data
public class MainDTO {
    public Double temp;
    public Double feelsLike;
    public Double tempMin;
    public Double tempMax;
    public Integer pressure;
    public Integer seaLevel;
    public Integer grndLevel;
    public Integer humidity;
    public Integer tempKf;
}

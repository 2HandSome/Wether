
package com.complexica.weather.dto.openweathermap;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WeatherForecastDTO implements Serializable {
    public String cod;
    public Integer message;
    public Integer cnt;
    public List<MapListDTO> list;
    public CityDTO city;
}

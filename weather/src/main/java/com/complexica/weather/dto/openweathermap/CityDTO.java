
package com.complexica.weather.dto.openweathermap;

import lombok.Data;
import lombok.NonNull;

@Data
public class CityDTO {
    public Integer id;
    public String name;
    public CoordDTO coord;
    public String country;
    public Integer population;
    public Integer timezone;
    public Integer sunrise;
    public Integer sunset;
}

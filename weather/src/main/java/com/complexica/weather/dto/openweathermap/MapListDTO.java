
package com.complexica.weather.dto.openweathermap;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MapListDTO {
    public Integer dt;
    public MainDTO main;
    public List<WeatherDTO> weather;
    public CloudsDTO clouds;
    public WindDTO wind;
    public Integer visibility;
    public Integer pop;
    public SysDTO sys;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("dt_txt")
    public LocalDateTime dtTxt;
    public RainDTO rain;
}

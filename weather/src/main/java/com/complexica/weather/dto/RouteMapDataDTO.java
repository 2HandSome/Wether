package com.complexica.weather.dto;

import lombok.Data;

import java.util.List;

@Data
public class RouteMapDataDTO {
    private List<WeatherDataDTO> routeMap;
}

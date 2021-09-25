package com.complexica.weather.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class RouteMapToStoreDTO {
    private String routeMapName;
    private List<WeatherValuesStoreDTO> itinerary = new ArrayList<>();


}

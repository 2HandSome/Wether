package com.complexica.weather.dto.routeMap;

import com.complexica.weather.dto.WeatherValuesStoreDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RouteMapUpdateResponseDTO {
    private String routeMapName;
    private List<WeatherValuesStoreDTO> itinerary = new ArrayList<>();
    private String id;

}

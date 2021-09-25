package com.complexica.weather.dto.routeMap;

import lombok.Data;

import java.util.List;

@Data
public class RouteMapSaveDto {
    private List<String> routeMap;
    private String RouteName;

}

package com.complexica.weather.services;

import com.complexica.weather.dto.RouteMapDataDTO;
import com.complexica.weather.dto.RouteMapToStoreDTO;
import com.complexica.weather.dto.routeMap.RouteMapDTO;
import com.complexica.weather.dto.routeMap.RouteMapUpdateResponseDTO;

import java.util.List;

public interface RouteService {
    RouteMapDataDTO viewRouteMapInfo(List<RouteMapDTO> routeMapDTOList);
    void storeRouteMap(RouteMapToStoreDTO routeMapToStoreDTO);
    void updateRouteMap(RouteMapUpdateResponseDTO routeMapUpdateResponseDTO);
    void deleteRoutMap(String routeName);
    List<RouteMapToStoreDTO> viewSavedRouteMaps();

    RouteMapUpdateResponseDTO findByRouteName(String routeName);
}

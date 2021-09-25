package com.complexica.weather.converter;

import com.complexica.weather.dto.RouteMapToStoreDTO;
import com.complexica.weather.dto.routeMap.RouteMapUpdateResponseDTO;
import com.complexica.weather.persistent.Route;
import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RouteConverter{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private WeatherDataConverter weatherDataConverter;

    public Route toEntity (RouteMapToStoreDTO routeMapToStoreDTO){
        return modelMapper.map(routeMapToStoreDTO, Route.class);
    }

    public RouteMapToStoreDTO toDto(Route route){

        return modelMapper.map(route, RouteMapToStoreDTO.class);
    }

    public List<RouteMapToStoreDTO> toDtoList(List<Route> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Route toEditRoute(@NotNull RouteMapUpdateResponseDTO routeMapUpdateResponseDTO, Route route) {
        if (!routeMapUpdateResponseDTO.getRouteMapName().isEmpty()) {
            route.setRouteMapName(routeMapUpdateResponseDTO.getRouteMapName());
        }
        route.setItinerary(weatherDataConverter.toEntityList(routeMapUpdateResponseDTO.getItinerary()));
        return route;
    }


}

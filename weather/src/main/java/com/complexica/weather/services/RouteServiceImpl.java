package com.complexica.weather.services;

import com.complexica.weather.converter.RouteConverter;
import com.complexica.weather.dto.RouteMapDataDTO;
import com.complexica.weather.dto.RouteMapToStoreDTO;
import com.complexica.weather.dto.WeatherDataDTO;
import com.complexica.weather.dto.routeMap.RouteMapDTO;
import com.complexica.weather.dto.routeMap.RouteMapUpdateResponseDTO;
import com.complexica.weather.exception.exception.RouteException;
import com.complexica.weather.persistent.Route;
import com.complexica.weather.repository.RouteMapDAO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class RouteServiceImpl extends BaseTimeService implements RouteService{

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private RouteMapDAO routeMapDAO;

    @Autowired
    private RouteConverter routeConverter;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RouteMapDataDTO viewRouteMapInfo(List<RouteMapDTO> routeMapDTOList){
        if(!routeMapDTOList.isEmpty()){
            RouteMapDataDTO routeMapDataDTO = new RouteMapDataDTO();
            List<WeatherDataDTO> weatherDataDTOList = new ArrayList<>();
            for (RouteMapDTO routeMap: routeMapDTOList) {
                WeatherDataDTO weatherDataDTO = weatherService.weatherByTime(routeMap);
                weatherDataDTOList.add(weatherDataDTO);
            }
            routeMapDataDTO.setRouteMap(weatherDataDTOList);
            return routeMapDataDTO;
        }else {
            log.info("Route map is empty");
        }
        return null;
    }

    @Override
    public void storeRouteMap(RouteMapToStoreDTO routeMapToStoreDTO) {
        if(routeMapDAO.findByRouteMapName(routeMapToStoreDTO.getRouteMapName()).isPresent()){
            throw new RouteException(message("RouteAlreadyExist"));
        }
        Route route = routeConverter.toEntity(routeMapToStoreDTO);
        routeMapDAO.save(route);
    }


    @Override
    public void updateRouteMap(RouteMapUpdateResponseDTO routeMapUpdateResponseDTO) {
        if(routeMapDAO.findById(routeMapUpdateResponseDTO.getId()).isPresent()){
            final Route route = routeMapDAO.findById(routeMapUpdateResponseDTO.getId()).get();
            routeMapDAO.save(routeConverter.toEditRoute(routeMapUpdateResponseDTO, route));
        }else{
            throw new RouteException(message("Route not found"));
        }
    }

    @Override
    public void deleteRoutMap(String routeName) {
        if(routeMapDAO.findByRouteMapName(routeName).isPresent()){
            Route route = routeMapDAO.findByRouteMapName(routeName).get();
            routeMapDAO.delete(route);
        }else{
            throw new RouteException(message("Route not found"));
        }
    }

    @Override
    public List<RouteMapToStoreDTO> viewSavedRouteMaps() {
        return routeConverter.toDtoList(routeMapDAO.findAll());
    }

    @Override
    public RouteMapUpdateResponseDTO findByRouteName(String routeName) {
        if(routeMapDAO.findByRouteMapName(routeName).isPresent()){
            Route route = routeMapDAO.findByRouteMapName(routeName).get();
            RouteMapUpdateResponseDTO routeMapDTO = modelMapper.map(route, RouteMapUpdateResponseDTO.class);
            return routeMapDTO;
        }else{
            throw  new RouteException(message("Route not found"));
        }
    }

}

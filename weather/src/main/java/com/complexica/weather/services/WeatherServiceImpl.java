package com.complexica.weather.services;

import com.complexica.weather.dto.WeatherDataDTO;
import com.complexica.weather.dto.WeatherValuesDTO;
import com.complexica.weather.dto.openweathermap.MapListDTO;
import com.complexica.weather.dto.openweathermap.WeatherForecastDTO;
import com.complexica.weather.dto.routeMap.RouteMapDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WeatherServiceImpl extends BaseTimeService implements WeatherService {

    @Value("${weather.url}")
    private String uri;

    @Value("${weather.api.id}")
    private String apiId;



    @Autowired
    private RestTemplate restTemplate;


    public WeatherServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Cacheable("weather")
    @Override
    public WeatherForecastDTO weatherForecast(String city) {
        try {
            WeatherForecastDTO weatherMap = this.restTemplate.getForObject(this.url(city), WeatherForecastDTO.class);
            return weatherMap;

        } catch (HttpClientErrorException e) {
            log.error("city not found");
        }
        return null;
    }

    @Override
    public WeatherDataDTO weatherByTime(RouteMapDTO routeMapDTO){
        WeatherDataDTO weatherDataDTO = new WeatherDataDTO();
        WeatherForecastDTO weatherForecastDTO = weatherForecast(routeMapDTO.getCity());
        List<MapListDTO> mapListDTOS = filterByData(weatherForecastDTO);
        if(isValidTime(routeMapDTO.getDateTime())){
            WeatherValuesDTO weatherValuesDTO = hourlyWeather(mapListDTOS, routeMapDTO.getDateTime());
            weatherDataDTO.setCityName(weatherForecastDTO.getCity().getName());
            weatherDataDTO.setCountryCode(weatherForecastDTO.getCity().getCountry());
            weatherDataDTO.setTime(weatherValuesDTO.getTime());
            weatherDataDTO.setTemperature(weatherValuesDTO.getTemperature());
            weatherDataDTO.setClouds(weatherValuesDTO.getClouds());
            return weatherDataDTO;
        }else{
            return null;
        }

    }


    private String url(String city) {
        return String.format(uri.concat("?q=%s").concat("&appid=%s").concat("&units=metric"), city, apiId);
    }

    private List<MapListDTO> filterByData(WeatherForecastDTO weatherForecastDTO){
        List<MapListDTO> mapListDTOS = new ArrayList<>();
        weatherForecastDTO.getList().stream().forEach(mapListDTO -> {
                    if(isValidTime(mapListDTO.getDtTxt())){
                        mapListDTOS.add(mapListDTO);
                    }
                }
        );
        return mapListDTOS;
    }

    private WeatherValuesDTO hourlyWeather(List<MapListDTO> mapListDTOList, LocalDateTime localDateTime){
        return find(localDateTime, mapListDTOList);
    }


}

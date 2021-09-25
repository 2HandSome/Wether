package com.complexica.weather.services;

import com.complexica.weather.dto.WeatherDataDTO;
import com.complexica.weather.dto.openweathermap.WeatherForecastDTO;
import com.complexica.weather.dto.routeMap.RouteMapDTO;

public interface WeatherService {
    WeatherForecastDTO weatherForecast(String city);
    WeatherDataDTO weatherByTime(RouteMapDTO routeMapDTO);

}

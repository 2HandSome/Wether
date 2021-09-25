package com.complexica.weather.controllers;

import com.complexica.weather.dto.openweathermap.WeatherForecastDTO;
import com.complexica.weather.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/forecast", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeatherForecastDTO> weatherForecastAverage() {

        WeatherForecastDTO london = weatherService.weatherForecast("London");
        return ResponseEntity.ok().body(london);
    }
}


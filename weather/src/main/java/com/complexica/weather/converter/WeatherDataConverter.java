package com.complexica.weather.converter;

import com.complexica.weather.dto.WeatherValuesStoreDTO;
import com.complexica.weather.persistent.WeatherValues;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherDataConverter {
    private ModelMapper modelMapper;

    public List<WeatherValues> toEntityList(List<WeatherValuesStoreDTO> weatherValuesDTO){
        List<WeatherValues> weatherValuesList = new ArrayList<>();
        if(!weatherValuesDTO.isEmpty()){
            for (WeatherValuesStoreDTO weatherValuesStoreDTO:weatherValuesDTO) {
                WeatherValues weatherValues = new WeatherValues();
                weatherValues.setCity(weatherValuesStoreDTO.getCity());
                weatherValuesList.add(weatherValues);
            }
        }
      return weatherValuesList;
    }

}

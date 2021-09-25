package com.complexica.weather.services;

import com.complexica.weather.dto.WeatherValuesDTO;
import com.complexica.weather.dto.openweathermap.MapListDTO;
import org.apache.logging.log4j.message.ReusableMessageFactory;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;

public class BaseTimeService {

    @Value("${weather.intervals.start}")
    private Integer staerFoDay;

    @Value("${weather.plus.hours}")
    private Integer plusHours;

    @Value("${weather.days.divide}")
    private Integer daysDivide;

    private static final Integer MINUTS = 60;

    protected String message(String message, Object... params) {
        return ReusableMessageFactory.INSTANCE.newMessage(message, params).getFormattedMessage();
    }

    protected Boolean isValidTime(LocalDateTime localDateTime){
        LocalDateTime from = localDateTime.toLocalDate().atStartOfDay().plusHours(staerFoDay);
        LocalDateTime to = from.plusHours(plusHours);
        if(localDateTime.isAfter(from) && localDateTime.isBefore(to)){
            return true;
        }
        return false;
    }


    public WeatherValuesDTO find(LocalDateTime localDataTime, List<MapListDTO> localDataTimeList) {
        Double leftTimePosition = 0.0;
        Double rightTimePosition = 0.0;
        Double timeToMinuts = 0.0;
        WeatherValuesDTO weatherValuesDTO = new WeatherValuesDTO();

        for(int i = 0; i < localDataTimeList.size(); i++){
            if(localDataTimeList.get(i).getDtTxt().equals(localDataTime)){
                localDataTimeList.get(i).getMain().getTemp();
                weatherValuesDTO.setTime(localDataTime);
                weatherValuesDTO.setTemperature(localDataTimeList.get(i).getMain().getTemp());
                weatherValuesDTO.setClouds(localDataTimeList.get(i).getClouds().getAll());
                return weatherValuesDTO;
            }
            if(localDataTimeList.get(i).getDtTxt().toLocalTime().isBefore(localDataTime.toLocalTime())){
                timeToMinuts = Double.valueOf((localDataTimeList.get(i).getDtTxt().getHour() * 60) + localDataTimeList.get(i).getDtTxt().getMinute());
                leftTimePosition = localDataTimeList.get(i).getMain().getTemp();
                weatherValuesDTO.setClouds(localDataTimeList.get(i).getClouds().getAll());
            }
            if(localDataTimeList.get(i).getDtTxt().toLocalTime().isAfter(localDataTime.toLocalTime())){
                rightTimePosition = localDataTimeList.get(i).getMain().getTemp();
                break;
            }

        }
        weatherValuesDTO.setTime(localDataTime);
        weatherValuesDTO.setTemperature(calculateFinallTemperature(calculateTemperatureCoefficient(leftTimePosition, rightTimePosition, daysDivide), timeToMinuts, localDataTime, leftTimePosition));
        return weatherValuesDTO;
    }

    protected Double calculateFinallTemperature(Double tempCalc, Double timeToMinuts, LocalDateTime localDateTime, Double leftTimePosition){
        Double  finalTime = ((localDateTime.getHour() * 60) + localDateTime.getMinute()) - timeToMinuts;
        return mathRound((tempCalc * finalTime) + leftTimePosition);
    }

    protected Double calculateTemperatureCoefficient(Double varOne, Double varTwo, Integer daysDivide){
        if(varOne > varTwo){
            double result = (varOne - varTwo) / (daysDivide * MINUTS);
            double changeSign = result * -1;
            return changeSign;
        }else if(varOne < varTwo){
            return (varTwo - varOne)/(daysDivide * MINUTS);
        }

        return varOne;
    }

    private Double mathRound(Double val){
        return (double) Math.round(val * 100) / 100;
    }
}

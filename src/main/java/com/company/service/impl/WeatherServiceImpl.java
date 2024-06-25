package com.company.service.impl;

import com.company.client.WeatherClient;
import com.company.dto.AddressDTO;
import com.company.dto.ResponseWeather;
import com.company.repository.AddressRepository;
import com.company.service.WeatherService;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherClient weatherClient;

    public WeatherServiceImpl( WeatherClient weatherClient) {

        this.weatherClient = weatherClient;
    }


    @Override
    public Integer getCurrentTemperature(String city) {
        String key = "712b36fd4a27a64240471440669ce45d";
        ResponseWeather result =  weatherClient.getTemperature(key,city);
        return result !=null ? result.getCurrent().getTemperature() : null;
    }
}

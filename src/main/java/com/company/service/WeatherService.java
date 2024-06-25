package com.company.service;

import org.springframework.stereotype.Service;


public interface WeatherService {
    Integer getCurrentTemperature(String city);
}

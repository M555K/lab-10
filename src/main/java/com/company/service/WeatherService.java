package com.company.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


public interface WeatherService {
    Integer getCurrentTemperature( @RequestParam("city") String city);
}

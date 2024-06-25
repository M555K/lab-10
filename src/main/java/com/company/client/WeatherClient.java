package com.company.client;

import com.company.dto.ResponseWeather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url="http://api.weatherstack.com", name="WEATHER-CLIENT")
public interface WeatherClient {
    @GetMapping("/current")
    ResponseWeather getTemperature(@RequestParam("access_key")  String key, @RequestParam("query") String city);

}

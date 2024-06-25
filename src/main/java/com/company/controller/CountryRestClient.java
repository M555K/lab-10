package com.company.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/country")
public class CountryRestClient {
    private final String URI ="https://restcountries.com/v3.1/name";
    private final RestTemplate restTemplate ;

    public CountryRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("{countryName}")
        public Object getCountry(@PathVariable("countryName") String name){
            String URL = URI +"/{countryName}";
            return restTemplate.getForObject(URL,Object.class,name);
        }
}

package com.company.service.impl;

import com.company.repository.AddressRepository;
import com.company.service.FlagService;
import org.springframework.stereotype.Service;

@Service
public class FlagServiceImpl implements FlagService {
    private final AddressRepository addressRepository;

    public FlagServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public String getFlagUrl(String countryName) {
        String URL = "https://restcountries.com/v3/name/" + countryName;
        return "";
    }
}

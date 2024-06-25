package com.company.service.impl;

import com.company.client.WeatherClient;
import com.company.dto.AddressDTO;
import com.company.entity.Address;
import com.company.exception.NotFoundException;
import com.company.repository.AddressRepository;
import com.company.service.AddressService;
import com.company.service.FlagService;
import com.company.service.WeatherService;
import com.company.util.MapperUtil;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final MapperUtil mapperUtil;
    private final WeatherService weatherService;
    private final FlagService flagService;


    public AddressServiceImpl(AddressRepository addressRepository, MapperUtil mapperUtil, WeatherService weatherService, FlagService flagService) {
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
        this.weatherService = weatherService;
        this.flagService = flagService;
    }

    @Override
    public AddressDTO findByAddressNo(String addressNo) {

        Address foundAddress = addressRepository.findByAddressNo(addressNo)
                .orElseThrow(() -> new NotFoundException("No Address Found!"));
        AddressDTO addressDTO = mapperUtil.convert(foundAddress, new AddressDTO());
        addressDTO.setCurrentTemperature(weatherService.getCurrentTemperature(addressDTO.getCity()));
        addressDTO.setFlag(flagService.getFlagUrl(addressDTO.getCountry()));

        return addressDTO;
    }

    @Override
    public AddressDTO update(String addressNo, AddressDTO address) {

        Address foundAddress = addressRepository.findByAddressNo(addressNo)
                .orElseThrow(() -> new NotFoundException("No Address Found!"));

        Address addressToUpdate = mapperUtil.convert(address, new Address());

        addressToUpdate.setAddressNo(addressNo);
        addressToUpdate.setId(foundAddress.getId());

        Address updatedAddress = addressRepository.save(addressToUpdate);

        return mapperUtil.convert(updatedAddress, new AddressDTO());

    }
}

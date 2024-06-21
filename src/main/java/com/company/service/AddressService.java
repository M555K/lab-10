package com.company.service;

import com.company.dto.AddressDTO;

public interface AddressService {

    AddressDTO findByAddressNo(String addressNo);

    AddressDTO update(String addressNo, AddressDTO addressDTO) ;

}

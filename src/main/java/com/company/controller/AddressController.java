package com.company.controller;

import com.company.client.WeatherClient;
import com.company.dto.AddressDTO;
import com.company.dto.ResponseWrapper;
import com.company.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    /*
    Endpoint: /api/v1/address/{addressNo}
    HTTP Status Code: 200

    JSON Response Body:
    "success": true
    "message": "Address <addressNo> is successfully retrieved."
    "code":200
    "data":<address data>
   */
    @GetMapping("/{addressNo}")
    public ResponseEntity<ResponseWrapper> getAddressesByNumber(@PathVariable String addressNo){
        AddressDTO addressDTO = addressService.findByAddressNo(addressNo);
        ResponseWrapper wrapper =  ResponseWrapper.builder().success(true)
                .message("Address "+addressNo+" is successfully retrieved.")
                .code(HttpStatus.OK.value())
                .data(addressDTO).build();
        return ResponseEntity.ok(wrapper);
    }


    /*
      Endpoint: /api/v1/address/{addressNo}

      JSON Response Body:
      <updated address data>
     */
    @PutMapping("/{addressNo}")
    public AddressDTO updateAddress(@PathVariable String addressNo, @RequestBody AddressDTO addressDTO){
        return addressService.update(addressNo,addressDTO);
    }


}

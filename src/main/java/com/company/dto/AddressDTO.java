package com.company.dto;

import com.company.enums.AddressType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDTO {
    @JsonIgnore
    private Long id;

    private String addressNo;

    private String street;
    private String country;
    private String state;
    private String city;
    private String postalCode;
    private String flag;

    private AddressType addressType;

    private Integer currentTemperature;
}

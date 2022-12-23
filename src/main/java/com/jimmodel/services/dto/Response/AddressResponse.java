package com.jimmodel.services.dto.Response;

import com.jimmodel.services.domain.Address;
import lombok.*;

@Data
public class AddressResponse {
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    public AddressResponse(Address address){
        this.address = address.getAddress();
        this.city = address.getCity();
        this.province = address.getProvince();
        this.postalCode = address.getPostalCode();
        this.country = address.getCountry();
    }
}

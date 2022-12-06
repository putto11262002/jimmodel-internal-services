package com.jimmodel.internalServices.dto.Request;

import com.jimmodel.internalServices.model.Address;
import lombok.Data;

import java.util.UUID;

@Data
public class AddressRequest {

    private UUID id;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    public Address toEntity(){
        return Address.builder()
                .id(this.id)
                .address(this.address)
                .city(this.city)
                .province(this.province)
                .postalCode(this.postalCode)
                .country(this.country)
                .build();
    }
}

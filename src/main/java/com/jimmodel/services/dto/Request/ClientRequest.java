package com.jimmodel.services.dto.Request;


import com.jimmodel.services.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientRequest {

    private UUID id;
    private String name;
    private AddressRequest address;

    public Client toEntity(){
        return Client.builder()
                .id(this.id)
                .address(this.address != null ? this.address.toEntity(): null)
                .name(this.name)
                .build();
    }
}

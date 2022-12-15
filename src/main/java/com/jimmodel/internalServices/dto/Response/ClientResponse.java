package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.domain.Client;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientResponse {
    private UUID id;
    private String name;
    private AddressResponse address;

    public ClientResponse(Client client){
        this.id = client.getId();
        this.name = client.getName();
        this.address = client.getAddress() != null ? new AddressResponse(client.getAddress()) : null;
    }

}

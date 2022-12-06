package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.model.Address;
import com.jimmodel.internalServices.model.Client;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

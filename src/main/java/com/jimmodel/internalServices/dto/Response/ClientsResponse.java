package com.jimmodel.internalServices.dto.Response;

import com.jimmodel.internalServices.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientsResponse {
    private Collection<ClientResponse> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Boolean isLast;

    public ClientsResponse(Page<Client> clientPage){
        this.pageSize = clientPage.getSize();
        this.totalPages = clientPage.getTotalPages();
        this.isLast = clientPage.isLast();
        this.content = clientPage.stream().map(client -> new ClientResponse(client)).collect(Collectors.toList());

    }

}

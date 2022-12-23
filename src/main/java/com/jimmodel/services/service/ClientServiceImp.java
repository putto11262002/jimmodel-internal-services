package com.jimmodel.services.service;

import com.jimmodel.services.exception.ResourceNotFoundException;
import com.jimmodel.services.exception.ValidationException;
import com.jimmodel.services.domain.BaseEntity;
import com.jimmodel.services.domain.Client;
import com.jimmodel.services.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service(value = "clientService")
public class ClientServiceImp implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private Validator validator;

    @Override
    public Client save(Client client) {
        Set<ConstraintViolation<BaseEntity>> violations = validator.validate(client);
        if (!violations.isEmpty()){
            throw new ValidationException(String.join(",",  violations.stream().map(violation -> violation.getMessage()).collect(Collectors.toList())));
        }
        return clientRepository.save(client);
    }

    @Override
    public Client saveById(UUID id, Client updatedClient) {
        Client client = this.findById(id);

        client.setAddress(updatedClient.getAddress());
        client.setName(updatedClient.getName());
        return clientRepository.save(client);
    }

    @Override
    public Client findById(UUID id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Client with id %s does not exist", id)));
    }

    @Override
    public Page<Client> findAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return clientRepository.findAll(pageable);
    }

    @Override
    public void deleteById(UUID id) {
        if(!clientRepository.existsById(id)){
            throw new ResourceNotFoundException(String.format("Client with id %s does not exist", id));
        }
        clientRepository.deleteById(id);
    }

    @Override
    public Page<Client> search(String searchTerm, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return clientRepository.search(searchTerm, pageable);
    }
}

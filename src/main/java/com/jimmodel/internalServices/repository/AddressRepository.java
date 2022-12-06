package com.jimmodel.internalServices.repository;

import com.jimmodel.internalServices.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}

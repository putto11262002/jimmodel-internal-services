package com.jimmodel.internalServices.repository;

import com.jimmodel.internalServices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StaffRepository extends JpaRepository<User, UUID> {

    public Optional<User> findOneByUsername(String email);
}

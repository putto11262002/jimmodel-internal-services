package com.jimmodel.internalServices.repository;

import com.jimmodel.internalServices.domain.ERole;
import com.jimmodel.internalServices.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, ERole> {

}

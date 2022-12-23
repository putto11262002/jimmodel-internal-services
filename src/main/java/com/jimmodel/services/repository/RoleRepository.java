package com.jimmodel.services.repository;

import com.jimmodel.services.domain.ERole;
import com.jimmodel.services.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, ERole> {

}

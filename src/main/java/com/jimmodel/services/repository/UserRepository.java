package com.jimmodel.services.repository;

import com.jimmodel.services.domain.Role;
import com.jimmodel.services.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    @Query(value = "select u from User u where lower(u.firstName) like concat('%', lower(:searchTerm), '%') or lower(u.lastName) like concat('%', lower(:searchTerm), '%')")
    Page<User> search(@Param(value = "searchTerm") String searchTerm, Pageable pageable);

    Boolean existsByUsername(String username);

    Boolean existsByRoles(Role role);
}

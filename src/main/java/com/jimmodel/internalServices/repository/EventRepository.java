package com.jimmodel.internalServices.repository;

import com.jimmodel.internalServices.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    Optional<Event> findByTypeAndId(String type, UUID id);

    Page<Event> findAllByType(String type, Pageable pageable);

    Boolean existsByTypeAndId(String type, UUID id);

    @Query(value = "select e from Event e where e.type = :type and lower(e.title) like concat('%', :searchTerm, '%')")
    Page<Event> search(@Param(value = "type") String type, @Param(value = "searchTerm") String searchTerm, Pageable pageable);
}

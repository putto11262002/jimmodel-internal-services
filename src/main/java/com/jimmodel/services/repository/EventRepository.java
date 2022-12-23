package com.jimmodel.services.repository;

import com.jimmodel.services.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    Optional<Event> findByTypeAndId(Event.TYPE type, UUID id);

    Page<Event> findAllByType(Event.TYPE type, Pageable pageable);

    Boolean existsByTypeAndId(Event.TYPE type, UUID id);

    @Query(value = "select e from Event e where e.type = :type and lower(e.title) like concat('%', :searchTerm, '%')")
    Page<Event> search(@Param(value = "type") Event.TYPE type, @Param(value = "searchTerm") String searchTerm, Pageable pageable);

    @Query(value = "select e from Event e left outer join e.slots s where s.startTimestamp > :date or s.startTimestamp = :date or s.endTimestamp < :date or s.endTimestamp = :date")
    Collection<Event> findAllBySlotsDate(@Param(value = "date") Instant date);
}

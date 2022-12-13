package com.jimmodel.internalServices.repository;

import com.jimmodel.internalServices.model.Role;
import com.jimmodel.internalServices.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Repository
public interface SlotRepository  extends JpaRepository<Slot, UUID> {

    @Query(value = "select s from Slot s inner join s.event where s.startTimestamp > :date or s.startTimestamp = :date or s.endTimestamp < :date or s.endTimestamp = :date")
    Collection<Slot> findAllSlotByDate(@Param(value = "date") LocalDateTime date);
}

package com.jimmodel.internalServices.repository;

import com.jimmodel.internalServices.domain.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
    @Query(value = "select m from Model m where lower(m.firstName) like concat('%', lower(:searchTerm), '%') or lower(m.lastName) like concat('%', lower(:searchTerm), '%') or lower(m.otherNames) like concat('%', lower(:searchTerm), '%')")
    Page<Model> search(@Param("searchTerm") String searchTerm, Pageable pageable);

    Page<Model> findAllByPublished(Boolean published, Pageable pageable);
}


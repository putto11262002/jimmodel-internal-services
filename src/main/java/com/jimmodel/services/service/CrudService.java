package com.jimmodel.services.service;

import org.springframework.data.domain.Page;

public interface CrudService<T, ID> {

    public T save(T t);

    public T findById(ID id);

    public Page<T> findAll(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    public T saveById(ID id, T t);

    public void deleteById(ID id);

    public Page<T> search(String searchTerm, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
}

package com.bonobo.micronaut.service;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.io.Serializable;
import java.util.Optional;

public interface IBaseService<E extends Serializable> {
    E save(E entity);

    Page<E> findAll(Pageable pageable);

    Optional<E> findOne(Long id);

    void delete(Long id);
}

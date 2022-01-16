package com.bonobo.micronaut.service;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.io.Serializable;
import java.util.Optional;

public interface IBaseService<D extends Serializable> {
    D save(D dto);

    Page<D> findAll(Pageable pageable);

    Optional<D> findOne(Long id);

    void delete(Long id);
}

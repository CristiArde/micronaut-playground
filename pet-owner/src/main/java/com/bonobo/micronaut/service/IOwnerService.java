package com.bonobo.micronaut.service;

import com.bonobo.micronaut.domain.Owner;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

import java.util.Optional;

public interface IOwnerService {
    Owner save(Owner owner);

    Page<Owner> findAll(Pageable pageable);

    Optional<Owner> findOne(Long id);

    void delete(Long id);
}

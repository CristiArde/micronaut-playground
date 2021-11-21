package com.bonobo.service;

import com.bonobo.domain.Specialty;

import java.util.Collection;
import java.util.Optional;

public interface SpecialtyService {
    Specialty save(Specialty specialty) throws Exception;

    Collection<Specialty> findAll() throws Exception;

    Optional<Specialty> findOne(Long id) throws Exception;

    void delete(Long id) throws Exception;
}

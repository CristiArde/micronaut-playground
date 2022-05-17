package com.bonobo.service;


import com.bonobo.service.dto.SpecialtyDto;

import java.util.Collection;
import java.util.Optional;

public interface SpecialtyService {
    SpecialtyDto save(SpecialtyDto specialty) throws Exception;

    Collection<SpecialtyDto> findAll() throws Exception;

    Optional<SpecialtyDto> findOne(Long id) throws Exception;

    void delete(Long id) throws Exception;
}

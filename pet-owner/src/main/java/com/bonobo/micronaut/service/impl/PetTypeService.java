package com.bonobo.micronaut.service.impl;

import com.bonobo.micronaut.domain.PetType;
import com.bonobo.micronaut.repository.PetTypeRepository;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;

@Singleton
@Transactional
public class PetTypeService extends BaseService<PetTypeRepository, PetType> {

    public PetTypeService(PetTypeRepository repository) {
        super(PetTypeService.class.getName(), repository);
    }
}

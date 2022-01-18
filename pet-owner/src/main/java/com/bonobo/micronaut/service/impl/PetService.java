package com.bonobo.micronaut.service.impl;

import com.bonobo.micronaut.domain.Pet;
import com.bonobo.micronaut.repository.PetRepository;
import com.bonobo.micronaut.service.dto.PetDto;
import com.bonobo.micronaut.service.mapper.PetMapper;

import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
@Transactional
public class PetService extends BaseService<Pet, PetDto, PetMapper, PetRepository> {

    public PetService(PetRepository repository, PetMapper mapper) {
        super(PetService.class.getName(), repository, mapper);
    }
}

package com.bonobo.micronaut.service.impl;

import com.bonobo.micronaut.domain.Pet;
import com.bonobo.micronaut.repository.PetRepository;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;

@Singleton
@Transactional
public class PetService extends BaseService<PetRepository, Pet> {

    public PetService(PetRepository repository) {
        super(PetService.class.getName(), repository);
    }
}
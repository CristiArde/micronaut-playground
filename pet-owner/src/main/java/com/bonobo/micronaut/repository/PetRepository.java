package com.bonobo.micronaut.repository;

import com.bonobo.micronaut.domain.Pet;
import io.micronaut.data.annotation.Repository;

import javax.persistence.EntityManager;

@Repository
public abstract class PetRepository extends BaseRepository<Pet> {
    protected PetRepository(EntityManager entityManager) {
        super(entityManager);
    }
}

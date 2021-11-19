package com.bonobo.micronaut.repository;

import com.bonobo.micronaut.domain.PetType;
import io.micronaut.data.annotation.Repository;

import javax.persistence.EntityManager;

@Repository
public abstract class PetTypeRepository extends BaseRepository<PetType> {
    protected PetTypeRepository(EntityManager entityManager) {
        super(entityManager);
    }
}

package com.bonobo.micronaut.repository;

import com.bonobo.micronaut.domain.PetType;
import io.micronaut.data.annotation.Repository;

import javax.persistence.EntityManager;

@Repository
public abstract class TypeRepository extends BaseRepository<PetType> {
    protected TypeRepository(EntityManager entityManager) {
        super(entityManager);
    }
}

package com.bonobo.micronaut.repository;

import com.bonobo.micronaut.domain.Owner;
import io.micronaut.data.annotation.Repository;

import javax.persistence.EntityManager;

@Repository
public abstract class OwnerRepository extends BaseRepository<Owner> {
    protected OwnerRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
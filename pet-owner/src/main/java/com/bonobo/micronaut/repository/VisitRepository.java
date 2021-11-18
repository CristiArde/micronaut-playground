package com.bonobo.micronaut.repository;

import com.bonobo.micronaut.domain.Visit;
import io.micronaut.data.annotation.Repository;

import javax.persistence.EntityManager;

@Repository
public abstract class VisitRepository extends BaseRepository<Visit> {
    protected VisitRepository(EntityManager entityManager) {
        super(entityManager);
    }
}

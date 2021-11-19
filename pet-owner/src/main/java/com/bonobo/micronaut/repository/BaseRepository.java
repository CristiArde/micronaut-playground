package com.bonobo.micronaut.repository;

import io.micronaut.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;


public abstract class BaseRepository<E extends Serializable> implements JpaRepository<E, Long> {

    @PersistenceContext
    protected final EntityManager entityManager;

    protected BaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public E mergeAndSave(E entity) {
        entity = entityManager.merge(entity);
        return save(entity);
    }
}

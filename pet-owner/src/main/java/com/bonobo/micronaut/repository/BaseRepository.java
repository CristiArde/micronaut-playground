package com.bonobo.micronaut.repository;

import io.micronaut.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


public abstract class BaseRepository<E> implements JpaRepository<E, Long> {

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

//    @Override
//    public <S extends R> List<S> saveAll(@Valid @NotNull Iterable<S> entities) {
//        ArrayList<S> o = new ArrayList<>();
//
//        for (S entity : entities) {
//            entity = entityManager.merge(entity);
//            o.add(save(entity));
//        }
//        return o;
//    }
}

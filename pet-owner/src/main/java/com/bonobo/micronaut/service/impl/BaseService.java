package com.bonobo.micronaut.service.impl;

import com.bonobo.micronaut.repository.BaseRepository;
import com.bonobo.micronaut.service.IBaseService;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.transaction.annotation.ReadOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Optional;

public abstract class BaseService<R extends BaseRepository<E>, E extends Serializable> implements IBaseService<E> {
    private final Logger log;
    private final R repository;

    public BaseService(String className, R repository) {
        this.log = LoggerFactory.getLogger(className);
        this.repository = repository;
    }


    @Override
    public E save(E entity) {
        log.debug("Request to save Owner : {}", entity);
        return this.repository.mergeAndSave(entity);
    }

    @Override
    @ReadOnly
    @Transactional
    public Page<E> findAll(Pageable pageable) {
        this.log.debug("Request to get all Owners");
        return this.repository.findAll(pageable);
    }

    @Override
    @ReadOnly
    @Transactional
    public Optional<E> findOne(Long id) {
        this.log.debug("Request to get Owner : {}", id);
        return this.repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        this.log.debug("Request to delete Owner : {}", id);
        this.repository.deleteById(id);
    }
}
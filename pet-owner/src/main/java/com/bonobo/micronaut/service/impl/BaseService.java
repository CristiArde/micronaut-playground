package com.bonobo.micronaut.service.impl;

import com.bonobo.micronaut.repository.BaseRepository;
import com.bonobo.micronaut.service.IBaseService;
import com.bonobo.micronaut.service.mapper.EntityMapper;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.transaction.annotation.ReadOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Optional;

public abstract class BaseService<E extends Serializable,
        D extends Serializable,
        M extends EntityMapper<D, E>,
        R extends BaseRepository<E>>  implements IBaseService<D> {

    private final Logger log;
    private final R repository;
    private final M mapper;

    public BaseService(String className, R repository, M mapper) {
        this.log = LoggerFactory.getLogger(className);
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public D save(D dto) {
        log.debug("Request to save Owner : {}", dto);
        E entity = this.mapper.toEntity(dto);
        entity = this.repository.mergeAndSave(entity);
        return this.mapper.toDto(entity);
    }

    @Override
    @ReadOnly
    @Transactional
    public Page<D> findAll(Pageable pageable) {
        this.log.debug("Request to get all Owners");
        return this.repository.findAll(pageable).map(this.mapper::toDto);
    }

    @Override
    @ReadOnly
    @Transactional
    public Optional<D> findOne(Long id) {
        this.log.debug("Request to get Owner : {}", id);
        return this.repository.findById(id).map(this.mapper::toDto);
    }

    @Override
    public void delete(Long id) {
        this.log.debug("Request to delete Owner : {}", id);
        this.repository.deleteById(id);
    }
}
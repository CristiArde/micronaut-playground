package com.bonobo.micronaut.service.impl;

import com.bonobo.micronaut.domain.Owner;
import com.bonobo.micronaut.repository.OwnerRepository;
import com.bonobo.micronaut.service.IOwnerService;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.transaction.annotation.ReadOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.Optional;

public class OwnerService implements IOwnerService {

    private final Logger log = LoggerFactory.getLogger(OwnerService.class);
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner save(Owner owner) {
        log.debug("Request to save Owner : {}", owner);
        return ownerRepository.mergeAndSave(owner);
    }

    @Override
    @ReadOnly
    @Transactional
    public Page<Owner> findAll(Pageable pageable) {
        log.debug("Request to get all Owners");
        return ownerRepository.findAll(pageable);
    }

    @Override
    @ReadOnly
    @Transactional
    public Optional<Owner> findOne(Long id) {
        log.debug("Request to get Owner : {}", id);
        return ownerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Owner : {}", id);
        ownerRepository.deleteById(id);
    }
}

package com.bonobo.micronaut.service.impl;

import com.bonobo.micronaut.domain.Owner;
import com.bonobo.micronaut.repository.OwnerRepository;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;

@Singleton
@Transactional
public class OwnerService extends BaseService<OwnerRepository, Owner>  {
    public OwnerService(OwnerRepository repository) {
        super(OwnerService.class.getName(), repository);
    }
}

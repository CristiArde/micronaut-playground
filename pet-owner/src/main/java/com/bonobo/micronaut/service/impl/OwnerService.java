package com.bonobo.micronaut.service.impl;

import com.bonobo.micronaut.domain.Owner;
import com.bonobo.micronaut.repository.OwnerRepository;
import com.bonobo.micronaut.service.dto.OwnerDto;
import com.bonobo.micronaut.service.mapper.OwnerMapper;

import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
@Transactional
public class OwnerService extends BaseService<Owner, OwnerDto, OwnerMapper, OwnerRepository> {

    public OwnerService(OwnerRepository repository, OwnerMapper mapper) {
        super(OwnerService.class.getName(), repository, mapper);
    }
}

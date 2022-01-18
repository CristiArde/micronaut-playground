package com.bonobo.micronaut.service.impl;

import com.bonobo.micronaut.domain.PetType;
import com.bonobo.micronaut.repository.PetTypeRepository;
import com.bonobo.micronaut.service.dto.PetTypeDto;
import com.bonobo.micronaut.service.mapper.PetTypeMapper;

import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
@Transactional
public class PetTypeService extends BaseService<PetType, PetTypeDto, PetTypeMapper, PetTypeRepository> {

    public PetTypeService(PetTypeRepository repository, PetTypeMapper mapper) {
        super(PetTypeService.class.getName(), repository, mapper);
    }
}

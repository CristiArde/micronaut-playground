package com.bonobo.micronaut.service.impl;

import com.bonobo.micronaut.domain.Visit;
import com.bonobo.micronaut.repository.VisitRepository;
import com.bonobo.micronaut.service.dto.VisitDto;
import com.bonobo.micronaut.service.mapper.VisitMapper;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;

@Singleton
@Transactional
public class VisitService extends BaseService<Visit, VisitDto, VisitMapper, VisitRepository> {

    public VisitService(VisitRepository repository) {
        super(VisitService.class.getName(), repository, mapper);
    }
}

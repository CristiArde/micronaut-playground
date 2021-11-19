package com.bonobo.micronaut.service.impl;

import com.bonobo.micronaut.domain.Visit;
import com.bonobo.micronaut.repository.VisitRepository;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;

@Singleton
@Transactional
public class VisitService extends BaseService<VisitRepository, Visit> {

    public VisitService(VisitRepository repository) {
        super(VisitService.class.getName(), repository);
    }
}

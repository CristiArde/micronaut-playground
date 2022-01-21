package com.bonobo.micronaut.web.rest;

import com.bonobo.micronaut.service.impl.BaseService;
import io.micronaut.context.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseResource<S extends BaseService<?, ?, ?, ?>> {
    protected final Logger log;
    protected final S service;

    @Value("${micronaut.application.name}")
    protected String applicationName;

    public BaseResource(Class<?> clazz, S service) {
        this.log = LoggerFactory.getLogger(clazz);
        this.service = service;
    }
}

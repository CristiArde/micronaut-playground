package com.bonobo.micronaut.util;

import com.bonobo.micronaut.domain.Owner;
import com.bonobo.micronaut.service.impl.OwnerService;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@Singleton
public class PetOwnerClient {
    private final Logger log = LoggerFactory.getLogger(PetOwnerClient.class);

    private final OwnerService ownerService;


    public PetOwnerClient(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public void performDatabaseOperations() {
        performFindAll();
    }

    protected void performFindAll() {
        log.info("------------------------------------------------------");
        log.info("Request to performFindAll");
        log.info("------------------------------------------------------");
        Page<Owner> pOwners = ownerService.findAll(Pageable.unpaged());

        if (CollectionUtils.isNotEmpty(pOwners.getContent())) {
            List<Owner> owners = pOwners.getContent();
            owners.forEach(owner -> log.info("Owner: {}, {}", owner.getFirstName(), owner.getLastName()));
        }
    }

}

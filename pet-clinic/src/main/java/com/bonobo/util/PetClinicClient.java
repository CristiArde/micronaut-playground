package com.bonobo.util;

import com.bonobo.domain.Specialty;
import com.bonobo.service.SpecialtyService;
import io.micronaut.core.util.CollectionUtils;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

@Singleton
public class PetClinicClient {
    private final SpecialtyService specialtyService;
    private final Logger log = LoggerFactory.getLogger(PetClinicClient.class);


    public PetClinicClient(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    public void performDatabaseOperations() {
        performFindAll();
    }

    protected void performFindAll() {
        log.info("------------------------------------------------------");
        log.info("Request to performFindAll");
        log.info("------------------------------------------------------");
        Collection<Specialty> specialties;
        try {
            specialties = specialtyService.findAll();
            if (CollectionUtils.isNotEmpty(specialties)) {
                specialties.forEach(specialty -> {
                    log.info("Specialty: {}", specialty.getName());
                });
            }
        } catch (Exception e) {
            log.error("Exception: {}", e.toString());
        }
    }
}

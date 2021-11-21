package com.bonobo.util;

import com.bonobo.domain.Doctor;
import com.bonobo.domain.Specialty;
import com.bonobo.repository.impl.SpecialtyRepositoryImpl;
import com.bonobo.service.ISpecialtyService;
import io.micronaut.core.util.CollectionUtils;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

@Singleton
public class PetClinicClient {
    private final ISpecialtyService specialtyService;
    private final Logger log = LoggerFactory.getLogger(PetClinicClient.class);


    public PetClinicClient(ISpecialtyService specialtyService) {
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

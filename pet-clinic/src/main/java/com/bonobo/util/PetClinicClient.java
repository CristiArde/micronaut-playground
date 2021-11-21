package com.bonobo.util;

import com.bonobo.domain.Doctor;
import com.bonobo.domain.Specialty;
import com.bonobo.service.DoctorService;
import com.bonobo.service.SpecialtyService;
import io.micronaut.core.util.CollectionUtils;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

@Singleton
public class PetClinicClient {
    private final SpecialtyService specialtyService;
    private final DoctorService doctorService;

    private final Logger log = LoggerFactory.getLogger(PetClinicClient.class);


    public PetClinicClient(SpecialtyService specialtyService, DoctorService doctorService) {
        this.specialtyService = specialtyService;
        this.doctorService = doctorService;
    }

    public void performDatabaseOperations() {
        performFindAll();
        Doctor vet = performSave();
        performFindOne(vet.getId());
        performDelete(vet);
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

    protected void performFindOne(Long id) {
        log.info("-----------------------------------------------------");
        log.info("Request to performFindOne for id: {}", id);
        log.info("-----------------------------------------------------");
        try {
            doctorService.findOne(id)
                            .ifPresentOrElse(doctor ->
                                            log.info("Vet: {}, {}", doctor.getFirstName(), doctor.getLastName()),
                                            () -> log.error("Exception:"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Doctor performSave() {
        Doctor vet = initVet();
        log.info("-----------------------------------------------------");
        log.info("Request to performSave for vet: {}", vet);
        log.info("-----------------------------------------------------");
        Doctor savedVet = null;
        try {
            savedVet = doctorService.save(vet);
        } catch (Exception e) {
            log.error("Exception: {}", e.toString());
        }
        return savedVet;
    }

    protected void performDelete(Doctor vet) {
        log.info("------------------------------------------------------");
        log.info("Request to performDelete for vet: {}", vet);
        log.info("-------------------------------------------------------");

        try {
            doctorService.delete(vet.getId());
        } catch (Exception e) {
            log.error("Exception: {}", e.toString());
        }
    }

    private Doctor initVet() {
        Doctor vet = new Doctor();
        vet.setFirstName("Foo");
        vet.setLastName("Bar");

        Specialty specialty = new Specialty();
        specialty.setName("Baz");

        vet.getSpecialties().add(specialty);

        return vet;
    }
}

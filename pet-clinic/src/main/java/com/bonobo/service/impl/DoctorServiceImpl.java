package com.bonobo.service.impl;

import com.bonobo.domain.Doctor;
import com.bonobo.domain.Specialty;
import com.bonobo.repository.DoctorRepository;
import com.bonobo.repository.SpecialtyRepository;
import com.bonobo.service.DoctorService;
import io.micronaut.core.util.CollectionUtils;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Singleton
public class DoctorServiceImpl implements DoctorService {
    private final Logger log = LoggerFactory.getLogger(DoctorServiceImpl.class);
    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository, SpecialtyRepository specialtyRepository) {
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;
    }

    private void saveSpecialties(Long vetId, Set<Specialty> specialties) {
        log.debug("Request to save Specialties : {}", specialties);
        if (CollectionUtils.isNotEmpty(specialties)) {
            specialties.forEach(specialty -> {
                try {
                    Specialty dbSpecialty = specialtyRepository.findByName(specialty.getName().toUpperCase().trim());
                    Long specialtyId = dbSpecialty != null ? dbSpecialty.getId() : specialtyRepository.save(specialty);
                    doctorRepository.saveVetSpecialty(vetId, specialtyId);
                } catch (Exception e) {
                    log.error("Exception: {}", e.toString());
                }
            });
        }
    }

    @Override
    public Doctor save(Doctor vet) throws Exception {
        log.debug("Request to save Vet : {}", vet);
        Doctor savedVet = null;
        try {
            Long vetId = doctorRepository.save(vet);
            saveSpecialties(vetId, vet.getSpecialties());
            savedVet = doctorRepository.findById(vetId);
        } catch (Exception e) {
            log.error("Exception: {}", e.toString());
        }
        return savedVet;
    }

    @Override
    public Collection<Doctor> findAll() throws Exception {
        log.debug("Request to get all Vets");
        Collection<Doctor> vets = doctorRepository.findAll();
        vets.forEach(vet -> {
            try {
                Set<Specialty> specialties = specialtyRepository.findByVetId(vet.getId());
                if (CollectionUtils.isNotEmpty(specialties)) {
                    vet.setSpecialties(specialties);
                }
            } catch (Exception e) {
                log.error("Exception: {}", e.toString());
            }
        });
        return vets;
    }

    @Override
    public Optional<Doctor> findOne(Long id) throws Exception {
        log.debug("Request to get Vet : {}", id);
        Doctor vet = doctorRepository.findById(id);
        if (vet != null) {
            try {
                Set<Specialty> specialties = specialtyRepository.findByVetId(vet.getId());
                if (CollectionUtils.isNotEmpty(specialties)) {
                    vet.setSpecialties(specialties);
                }
            } catch (Exception e) {
                log.error("Exception: {}", e.toString());
            }
        }
        return Optional.ofNullable(vet);
    }

    @Override
    public void delete(Long id) throws Exception {
        log.debug("Request to delete Vet : {}", id);
        this.findOne(id).ifPresentOrElse(doctor -> {
            if (CollectionUtils.isNotEmpty(doctor.getSpecialties())) {
                try {
                    doctorRepository.deleteVetSpecialtyById(doctor.getId());
                    /** Delete vet */
                    doctorRepository.deleteById(id);
                } catch (Exception e) {
                    log.debug("Request to delete Vet : {} did not complete successfully", id);
                    e.printStackTrace();
                }
            }
        }, () -> log.debug("The doctorVet ID:{} does not exists", id));
    }
}

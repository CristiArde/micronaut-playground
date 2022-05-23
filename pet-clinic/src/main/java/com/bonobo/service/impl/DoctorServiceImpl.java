package com.bonobo.service.impl;

import com.bonobo.domain.Doctor;
import com.bonobo.domain.Specialty;
import com.bonobo.repository.DoctorRepository;
import com.bonobo.repository.SpecialtyRepository;
import com.bonobo.service.DoctorService;
import com.bonobo.service.dto.DoctorDto;
import com.bonobo.service.dto.SpecialtyDto;
import com.bonobo.service.mapper.DoctorMapper;
import com.bonobo.service.mapper.SpecialtyMapper;
import io.micronaut.core.util.CollectionUtils;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Singleton
public class DoctorServiceImpl implements DoctorService {
    private final Logger log = LoggerFactory.getLogger(DoctorServiceImpl.class);
    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;
    private final DoctorMapper doctorMapper;
    private final SpecialtyMapper specialtyMapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository, SpecialtyRepository specialtyRepository, DoctorMapper doctorMapper, SpecialtyMapper specialtyMapper) {
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;
        this.doctorMapper = doctorMapper;
        this.specialtyMapper = specialtyMapper;
    }

    private void saveSpecialties(Long vetId, Set<SpecialtyDto> specialties) {
        log.debug("Request to save Specialties : {}", specialties);
        if (CollectionUtils.isNotEmpty(specialties)) {
            specialties.forEach(specialty -> {
                try {
                    Specialty dbSpecialty = specialtyRepository.findByName(specialty.getName().toUpperCase().trim());
                    Long specialtyId = dbSpecialty != null ? dbSpecialty.getId() : specialtyRepository.save(specialtyMapper.toEntity(specialty));
                    doctorRepository.saveVetSpecialty(vetId, specialtyId);
                } catch (Exception e) {
                    log.error("Exception: {}", e.toString());
                }
            });
        }
    }

    @Override
    public DoctorDto save(DoctorDto vetDto) throws Exception {
        log.debug("Request to save Vet : {}", vetDto);
        Doctor savedVet = null;
        try {
            Long vetId = doctorRepository.save(doctorMapper.toEntity(vetDto));
            saveSpecialties(vetId, vetDto.getSpecialties());
            savedVet = doctorRepository.findById(vetId);
        } catch (Exception e) {
            log.error("Exception: {}", e.toString());
        }
        return doctorMapper.toDto(savedVet);
    }

    @Override
    public Collection<DoctorDto> findAll() throws Exception {
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
        return doctorMapper.toDto((List<Doctor>) vets);
    }

    @Override
    public Optional<DoctorDto> findOne(Long id) throws Exception {
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
        return Optional.ofNullable(doctorMapper.toDto(vet));
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

    @Override
    public void updateVetAverageRating(Long id, Double rating) throws Exception {
        log.debug("Request to update vet rating, id: {}, rating: {}", id, rating);
        Optional<DoctorDto> oVetDTO = findOne(id);
        if (oVetDTO.isPresent()) {
            DoctorDto vetDTO = oVetDTO.get();

            Double averageRating = vetDTO.getAverageRating() != null ? vetDTO.getAverageRating() : 0D;
            Long ratingCount = vetDTO.getRatingCount() != null ? vetDTO.getRatingCount() : 0L;
            Double newAvgRating = ((averageRating * ratingCount) + rating) / (ratingCount + 1);
            Long newRatingCount = ratingCount + 1;

            doctorRepository.updateVetAverageRating(id, newAvgRating, newRatingCount);
        }
    }
}

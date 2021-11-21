package com.bonobo.service.impl;

import com.bonobo.domain.Specialty;
import com.bonobo.repository.ISpecialtyRepository;
import com.bonobo.service.ISpecialtyService;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Optional;

@Singleton
public class SpecialtyServiceImpl implements ISpecialtyService {

    private final Logger log = LoggerFactory.getLogger(SpecialtyServiceImpl.class);
    private final ISpecialtyRepository specialtyRepository;

    public SpecialtyServiceImpl(ISpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Specialty save(Specialty specialty) throws Exception {
        log.debug("Request to save Specialty : {}", specialty);
        Long specialtyId = specialtyRepository.save(specialty);
        return specialtyRepository.findById(specialtyId);
    }

    @Override
    public Collection<Specialty> findAll() throws Exception {
        log.debug("Request to get all Specialties");
        return specialtyRepository.findAll();
    }

    @Override
    public Optional<Specialty> findOne(Long id) throws Exception {
        log.debug("Request to get Specialty : {}", id);
        return Optional.ofNullable(specialtyRepository.findById(id));
    }

    @Override
    public void delete(Long id) throws Exception {
        log.debug("Request to delete Specialty : {}", id);
        specialtyRepository.deleteById(id);
    }
}

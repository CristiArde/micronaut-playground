package com.bonobo.service.impl;

import com.bonobo.domain.Specialty;
import com.bonobo.repository.SpecialtyRepository;
import com.bonobo.service.SpecialtyService;
import com.bonobo.service.dto.SpecialtyDto;
import com.bonobo.service.mapper.SpecialtyMapper;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class SpecialtyServiceImpl implements SpecialtyService {

    private final Logger log = LoggerFactory.getLogger(SpecialtyServiceImpl.class);
    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository, SpecialtyMapper specialtyMapper) {
        this.specialtyRepository = specialtyRepository;
        this.specialtyMapper = specialtyMapper;
    }

    @Override
    public SpecialtyDto save(SpecialtyDto specialtyDto) throws Exception {
        log.debug("Request to save Specialty : {}", specialtyDto);
        Specialty specialty = specialtyMapper.toEntity(specialtyDto);
        Long specialtyId = specialtyRepository.save(specialty);
        return specialtyMapper.toDto(specialtyRepository.findById(specialtyId));
    }

    @Override
    public Collection<SpecialtyDto> findAll() throws Exception {
        log.debug("Request to get all Specialties");
        return specialtyRepository.findAll()
                .stream()
                .map(specialtyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SpecialtyDto> findOne(Long id) throws Exception {
        log.debug("Request to get Specialty : {}", id);
        return Optional.ofNullable(specialtyRepository.findById(id)).map(specialtyMapper::toDto);
    }

    @Override
    public void delete(Long id) throws Exception {
        log.debug("Request to delete Specialty : {}", id);
        specialtyRepository.deleteById(id);
    }
}

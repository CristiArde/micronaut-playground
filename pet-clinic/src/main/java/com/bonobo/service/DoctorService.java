package com.bonobo.service;

import com.bonobo.service.dto.DoctorDto;

import java.util.Collection;
import java.util.Optional;

public interface DoctorService {

    DoctorDto save(DoctorDto vet) throws Exception;

    Collection<DoctorDto> findAll() throws Exception;

    Optional<DoctorDto> findOne(Long id) throws Exception;

    void delete(Long id) throws Exception;

    /**
     * Update average rating for a vet
     *
     * @param id
     * @param rating
     */
    void updateVetAverageRating(Long id, Double rating) throws Exception;
}

package com.bonobo.service;

import com.bonobo.domain.Doctor;

import java.util.Collection;
import java.util.Optional;

public interface DoctorService {

    Doctor save(Doctor vet) throws Exception;

    Collection<Doctor> findAll() throws Exception;

    Optional<Doctor> findOne(Long id) throws Exception;

    void delete(Long id) throws Exception;

}

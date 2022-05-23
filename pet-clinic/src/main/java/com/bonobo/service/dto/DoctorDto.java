package com.bonobo.service.dto;

import io.micronaut.core.annotation.Introspected;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Introspected
public class DoctorDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Double averageRating;
    private Long ratingCount;
    private Set<SpecialtyDto> specialties = new HashSet<>();

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Long getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Long ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<SpecialtyDto> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<SpecialtyDto> specialties) {
        this.specialties = specialties;
    }
}

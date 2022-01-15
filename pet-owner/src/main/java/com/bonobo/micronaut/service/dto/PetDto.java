package com.bonobo.micronaut.service.dto;

import io.micronaut.core.annotation.Introspected;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Introspected
public class PetDto implements Serializable {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Set<VisitDto> visits = new HashSet<>();
    private PetTypeDto type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<VisitDto> getVisits() {
        return visits;
    }

    public void setVisits(Set<VisitDto> visits) {
        this.visits = visits;
    }

    public PetTypeDto getType() {
        return type;
    }

    public void setType(PetTypeDto type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetDto petDto)) return false;
        return Objects.equals(id, petDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

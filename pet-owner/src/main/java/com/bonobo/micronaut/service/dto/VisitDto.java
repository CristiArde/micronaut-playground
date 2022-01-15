package com.bonobo.micronaut.service.dto;

import io.micronaut.core.annotation.Introspected;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Introspected
public class VisitDto implements Serializable {
    private Long id;
    private LocalDate visitDate;
    private String description;
    private Long petId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitDto visitDto)) return false;
        return Objects.equals(id, visitDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

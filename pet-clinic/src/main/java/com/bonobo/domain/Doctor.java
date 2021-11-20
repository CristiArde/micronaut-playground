package com.bonobo.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Doctor implements Serializable {

    @NotNull
    private Long id;
    private String firsName;
    private String lastName;
    private final Set<Specialty> specialties = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void addSpecialty(Specialty speciality) {
        this.specialties.add(speciality);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialities=" + specialties +
                '}';
    }
}
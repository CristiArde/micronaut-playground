package com.bonobo.domain;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Introspected
public class Speciality implements Serializable {

    @NotNull
    private Long id;
    private String name;

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

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.bonobo.micronaut.domain.dto;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "owners", schema = "petowner")
public class Owner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "telephone")
    private String telephone;
}

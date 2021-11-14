package com.bonobo.micronaut.controller;

import com.bonobo.micronaut.domain.Owner;
import com.bonobo.micronaut.repository.OwnerRepository;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

import java.util.List;

@Controller("/")
public class MyController {

    private final OwnerRepository ownerRepository;

    public MyController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }


//    @Get
//    public List<Owner> getOwners() {
//        return this.ownerRepository.findAll();
//    }

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "Hello World";
    }

}

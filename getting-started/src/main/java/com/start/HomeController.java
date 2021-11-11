package com.start;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

/*****************************************************************************
 * //TODO Add class/interface description
 *
 * @author CRAR
 * @since 11 Nov 2021
 ****************************************************************************/

@Controller("/")
public class HomeController {
    
    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "Hello World";
    }
}

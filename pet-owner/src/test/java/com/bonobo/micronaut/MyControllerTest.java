package com.bonobo.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class MyControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

//    @Test
//    public void supplyAnInvalidOrderTriggersValidationFailure() {
//        HttpClientResponseException thrown = assertThrows(HttpClientResponseException.class, () -> {
//            client.toBlocking().exchange(HttpRequest.GET("/genres/list?order=foo"));
//        });
//
//        assertNotNull(thrown.getResponse());
//        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());
//    }

    @Test
    public void testIndex() {
        HttpRequest<String> request = HttpRequest.GET("/");
        String body = this.client.toBlocking().retrieve(request);

        assertNotNull(body);
        assertEquals("Hello World", body);
    }

}

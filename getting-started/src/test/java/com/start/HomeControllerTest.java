package com.start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

/*****************************************************************************
 * //TODO Add class/interface description
 *
 * @author CRAR
 * @since 11 Nov 2021
 ****************************************************************************/
@MicronautTest
public class HomeControllerTest {
    @Inject
    @Client("/")
    HttpClient client;
    
    @Test
    public void testIndex() {
        HttpRequest<String> request = HttpRequest.GET("/");
        String body = this.client.toBlocking().retrieve(request);
    
        assertNotNull(body);
        assertEquals("Hello World", body);
    }
}

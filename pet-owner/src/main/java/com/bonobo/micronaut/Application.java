package com.bonobo.micronaut;

import com.bonobo.micronaut.util.PetOwnerClient;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;

@Singleton
public class Application {

    private final PetOwnerClient petOwnerClient;

    public Application(PetOwnerClient petOwnerClient) {
        this.petOwnerClient = petOwnerClient;
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

    @EventListener
    void init(StartupEvent event) {
        petOwnerClient.performDatabaseOperations();
    }
}

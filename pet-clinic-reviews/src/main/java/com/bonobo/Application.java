package com.bonobo;

import com.bonobo.util.PetClinicClient;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;

@Singleton
public class Application {
    private final PetClinicClient petClinicClient;

    public Application(PetClinicClient petClinicClient) {
        this.petClinicClient = petClinicClient;
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

    @EventListener
    void init(StartupEvent event) {
        this.petClinicClient.performDatabaseOperations();
    }
}

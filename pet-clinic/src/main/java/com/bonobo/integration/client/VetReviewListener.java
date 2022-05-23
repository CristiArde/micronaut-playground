package com.bonobo.integration.client;

import com.bonobo.integration.domain.VetReviewDto;
import com.bonobo.service.DoctorService;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@KafkaListener
public class VetReviewListener {
    private static final Logger log = LoggerFactory.getLogger(VetReviewListener.class);

    private final DoctorService vetService;

    public VetReviewListener(DoctorService vetService) {
        this.vetService = vetService;
    }

    @Topic("vet-reviews")
    public void receive(@MessageBody VetReviewDto vetReview) {
        log.info("Received: vetReview -> {}", vetReview);
        try {
            vetService.updateVetAverageRating(vetReview.getVetId(), vetReview.getRating());
        } catch (Exception e) {
            log.error("Exception occurred: {}", e.toString());
        }
    }
}

package com.bonobo.util;

import com.bonobo.domain.VetReview;
import com.bonobo.service.impl.VetReviewService;
import com.mongodb.client.MongoClient;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Singleton
@Requires(beans = MongoClient.class)
public class PetClinicClient {
    private final Logger log = LoggerFactory.getLogger(PetClinicClient.class);
    private final VetReviewService vetReviewService;

    public PetClinicClient(VetReviewService vetReviewService) {
        this.vetReviewService = vetReviewService;
    }

    public void performDatabaseOperations() {
        performFindAll();
        VetReview vetReview = performSave();
        performFindByReviewId(vetReview.getReviewId());
        performDelete(vetReview);
        performFindAll();
    }


    protected void performFindAll() {
        log.info("-----------------------------------------------------");
        log.info("Request to perform findAll");
        log.info("-----------------------------------------------------");
        List<VetReview> vetReviews = this.vetReviewService.findAll();
        for (VetReview vetReview : vetReviews) {
            log.info("Vet Review: {} | Vet Id: {}", vetReview.getReviewId(),vetReview.getVetId());
        }
    }

    protected void performFindByReviewId(String reviewId) {
        log.info("-----------------------------------------------------");
        log.info("Request to perform findByReviewId {}", reviewId);
        log.info("-----------------------------------------------------");
        VetReview vetReview = vetReviewService.findByReviewId(reviewId);
        log.info("Review: {}", vetReview);
    }

    protected VetReview performSave() {
        VetReview vetReview = new VetReview(
                UUID.randomUUID().toString(),
                1L,
                3.5,
                "Good experience",
                LocalDate.now());
        log.info("-----------------------------------------------------");
        log.info("Request to performSave for vet review: {}", vetReview);
        log.info("-----------------------------------------------------");
        return vetReviewService.save(vetReview);
    }

    protected void performDelete(VetReview vetReview) {
        log.info("------------------------------------------------------");
        log.info("Request to performDelete for vet review: {}", vetReview);
        log.info("-------------------------------------------------------");
        vetReviewService.delete(vetReview.getReviewId());
    }
}

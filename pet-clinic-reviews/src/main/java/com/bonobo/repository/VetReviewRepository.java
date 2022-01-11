package com.bonobo.repository;

import com.bonobo.domain.VetReview;

import java.util.List;

public interface VetReviewRepository {
    List<VetReview> findAll();

    VetReview findByReviewId(String reviewId);

    VetReview save(VetReview vetReview);

    void delete(String reviewId);
}

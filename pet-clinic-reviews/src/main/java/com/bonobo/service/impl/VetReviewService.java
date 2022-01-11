package com.bonobo.service.impl;

import com.bonobo.domain.VetReview;

import java.util.List;

public interface VetReviewService {
    List<VetReview> findAll();
    VetReview findByReviewId(String reviewId);
    VetReview save(VetReview vetReview);
    void delete(String reviewId);
}

package com.bonobo.service.impl;

import com.bonobo.service.dto.VetReviewDto;

import java.util.List;

public interface VetReviewService {
    List<VetReviewDto> findAll();
    VetReviewDto findByReviewId(String reviewId);
    VetReviewDto save(VetReviewDto vetReview);
    void delete(String reviewId);
}

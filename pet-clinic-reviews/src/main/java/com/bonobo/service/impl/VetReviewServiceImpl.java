package com.bonobo.service.impl;

import com.bonobo.domain.VetReview;
import com.bonobo.repository.VetReviewRepository;
import com.bonobo.service.dto.VetReviewDto;
import com.bonobo.service.mapper.VetReviewMapper;


import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class VetReviewServiceImpl implements VetReviewService {

    private final VetReviewRepository vetReviewRepository;

    private final VetReviewMapper vetReviewMapper;

    public VetReviewServiceImpl(VetReviewRepository vetReviewRepository, VetReviewMapper vetReviewMapper) {
        this.vetReviewRepository = vetReviewRepository;
        this.vetReviewMapper = vetReviewMapper;
    }

    @Override
    public List<VetReviewDto> findAll() {
        return vetReviewRepository.findAll().stream()
                .map(vetReviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public VetReviewDto findByReviewId(String reviewId) {
        return vetReviewMapper.toDto(vetReviewRepository.findByReviewId(reviewId));
    }

    @Override
    public VetReviewDto save(VetReviewDto vetReviewDto) {
        VetReview vetReview = vetReviewMapper.toEntity(vetReviewDto);
        return vetReviewMapper.toDto(vetReviewRepository.save(vetReview));
    }

    @Override
    public void delete(String reviewId) {
        vetReviewRepository.delete(reviewId);
    }
}

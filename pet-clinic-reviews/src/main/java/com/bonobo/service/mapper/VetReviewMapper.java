package com.bonobo.service.mapper;


import com.bonobo.domain.VetReview;
import com.bonobo.service.dto.VetReviewDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330", uses = {})
public interface VetReviewMapper extends EntityMapper<VetReviewDto, VetReview> {

    default VetReview fromReviewId(String reviewId) {
        if (reviewId == null) {
            return null;
        }
        VetReview vetReview = new VetReview();
        vetReview.setReviewId(reviewId);
        return vetReview;
    }
}

package com.bonobo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDate;

public class VetReview {

    private String reviewId;
    private Long vetId;
    private double rating;
    private String comment;
    private LocalDate dateAdded;


    @BsonCreator
    @JsonCreator
    public VetReview(@JsonProperty("reviewId")
                     @BsonProperty("reviewId") String reviewId,
                     @JsonProperty("vetId")
                     @BsonProperty("vetId") Long vetId,
                     @JsonProperty("rating")
                     @BsonProperty("rating") double rating, @JsonProperty("comment")
                     @BsonProperty("comment") String comment,
                     @JsonProperty("dateAdded")
                     @BsonProperty("dateAdded") LocalDate dateAdded) {
        this.reviewId = reviewId;
        this.vetId = vetId;
        this.rating = rating;
        this.comment = comment;
        this.dateAdded = dateAdded;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public Long getVetId() {
        return vetId;
    }

    public void setVetId(Long vetId) {
        this.vetId = vetId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
}

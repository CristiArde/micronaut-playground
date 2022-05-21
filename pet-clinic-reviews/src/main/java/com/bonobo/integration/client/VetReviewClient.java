package com.bonobo.integration.client;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.http.annotation.Body;


@KafkaClient
public interface VetReviewClient {
    //@Topic("vet-reviews")
    //void send(@Body VetReviewDto vetReview);
}


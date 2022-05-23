package com.bonobo.integration.client;

import com.bonobo.service.dto.VetReviewDto;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.MessageBody;


@KafkaClient
public interface VetReviewClient {
    @Topic("vet-reviews")
    void send(@MessageBody VetReviewDto vetReview);
}


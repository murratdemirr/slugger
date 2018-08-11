package com.demir.core;

import com.demir.core.email.control.EmailRepository;
import com.demir.core.kafka.KafkaConsumerClient;

import java.util.List;

public class BulkProcessor implements Runnable {

    public static final long FIXED_RATE = 60000;

    private KafkaConsumerClient kafkaConsumerClient;
    private EmailRepository repository;

    public BulkProcessor(EmailRepository repository, KafkaConsumerClient kafkaConsumerClient) {
        this.repository = repository;
        this.kafkaConsumerClient = kafkaConsumerClient;
    }


    @Override
    public void run() {
        System.out.println("job is running....");
        final List<String> emails = kafkaConsumerClient.fetchFromQueue();
        repository.save(emails);
        System.out.println("job is fnished....");
    }
}

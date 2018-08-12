package com.demir.core;

import com.demir.core.email.control.EmailRepository;
import com.demir.core.kafka.QueueConsumer;

import java.util.List;

public class BulkProcessor implements Runnable {

    private QueueConsumer kafkaConsumerClient;
    private EmailRepository repository;

    public BulkProcessor(EmailRepository repository, QueueConsumer kafkaConsumerClient) {
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

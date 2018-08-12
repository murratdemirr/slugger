package com.demir.core;

import com.demir.core.email.control.EmailRepository;
import com.demir.core.email.entity.Email;
import com.demir.core.kafka.QueueConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        final String batchId = UUID.randomUUID().toString();
        Map<String, Long> map = kafkaConsumerClient.fetchFromQueue();

        List<Email> emails = new ArrayList<>();
        map.forEach((email, count) -> {
            Email entity = new Email();
            entity.setEmail(email);
            entity.setBatchId(batchId);
            entity.setEncounteredTimes(count);
            emails.add(entity);
        });

        repository.save(emails);
        System.out.println("job is fnished....");
    }
}

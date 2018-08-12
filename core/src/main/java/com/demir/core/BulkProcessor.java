package com.demir.core;

import com.demir.core.email.control.EmailRepository;
import com.demir.core.email.entity.Email;
import com.demir.core.kafka.QueueConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BulkProcessor implements Runnable {

    private QueueConsumer kafkaConsumerClient;
    private EmailRepository repository;

    public BulkProcessor(EmailRepository repository, QueueConsumer queueConsumer) {
        this.repository = repository;
        this.kafkaConsumerClient = queueConsumer;
    }

    @Override
    public void run() {
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
    }
}

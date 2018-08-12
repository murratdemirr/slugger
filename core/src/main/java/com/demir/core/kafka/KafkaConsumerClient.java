package com.demir.core.kafka;

import com.demir.core.BulkProcessor;
import com.demir.core.email.control.EmailRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Demir
 * <p>
 * 8/10/18 11:18 AM
 */
@Component
public class KafkaConsumerClient {

    @Value("${spring.kafka.template.default-topic}")
    String topicName;
    @Autowired
    KafkaConsumer<String, String> consumer;
    @Autowired
    TaskScheduler taskScheduler;
    @Autowired
    EmailRepository emailRepository;

    private boolean inProgress = false;

    public List<String> fetchFromQueue() {
        consumer.subscribe(Collections.singletonList(topicName));
        ConsumerRecords<String, String> records = consumer.poll(5000);
        List<String> data = new ArrayList<>();
        for (ConsumerRecord<String, String> record : records) {
            data.add(record.value());
        }
        return data;
    }

    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void topicListener(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println("Event fire start");
        if (!inProgress) {
            System.out.println("Inprogres....");
            inProgress = true;
            triggerSchedule();
        }
        System.out.println("Event Fire end");
    }

    @Async
    public synchronized void triggerSchedule() {
        try {
            System.out.println("Trigger Schedule");
            Thread.sleep(5 * 60000);
            System.out.println("After thread sleep");
            taskScheduler.scheduleAtFixedRate(new BulkProcessor(emailRepository, this), 5 * 60000);
        } catch (InterruptedException ex) {
            inProgress = false;
            System.out.println("Not Inprogress");
        }
    }


}

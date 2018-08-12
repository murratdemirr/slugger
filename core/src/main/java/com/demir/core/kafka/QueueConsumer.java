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

import java.util.*;


/**
 * @author Demir
 * <p>
 * 8/10/18 11:18 AM
 */
@Component
public class QueueConsumer {

    @Value("${spring.kafka.template.default-topic}")
    String topicName;
    @Autowired
    KafkaConsumer<String, String> consumer;
    @Autowired
    TaskScheduler taskScheduler;
    @Autowired
    EmailRepository emailRepository;

    private boolean inProgress = false;

    public Map<String, Long> fetchFromQueue() {
        consumer.subscribe(Collections.singletonList(topicName));
        ConsumerRecords<String, String> records = consumer.poll(5000);

        // Email / Count
        Map<String, Long> map = new HashMap<>();
        for (ConsumerRecord<String, String> record : records) {
            final String email = record.value();
            if (map.containsKey(email)) {
                map.replace(email, map.get(email) + 1);
            } else {
                map.put(email, Long.valueOf(1));
            }
        }
        return map;
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
            Thread.sleep(60000);
            System.out.println("After thread sleep");
            taskScheduler.scheduleAtFixedRate(new BulkProcessor(emailRepository, this), 60000);
        } catch (InterruptedException ex) {
            inProgress = false;
            System.out.println("Not Inprogress");
        }
    }


}

package com.demir.core.kafka;

import com.demir.core.ProcessorListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CountDownLatch;


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
    ProcessorListener listener;

    private CountDownLatch latch = new CountDownLatch(1);

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
        if (latch.getCount() == 1) {
            listener.executeAsynchronously();
        }
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}

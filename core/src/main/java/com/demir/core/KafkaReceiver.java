package com.demir.core;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Demir
 *
 * 8/10/18 11:18 AM
 */
@Component
public class KafkaReceiver {

    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void receiveTopic1(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println("Receiver on topic1: " + consumerRecord.toString());
    }


}

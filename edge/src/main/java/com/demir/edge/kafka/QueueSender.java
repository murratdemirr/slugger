package com.demir.edge.kafka;

import com.demir.edge.email.control.DomainHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Demir
 * <p>
 * 8/10/18 2:00 PM
 */
@Service
public class QueueSender {

    private static final Logger LOG = LoggerFactory.getLogger(QueueSender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Async
    public void send(String message) {
        if (DomainHelper.isDomainValid(message)) {
            LOG.info("sending message='{}' to topic='{}'", message, topic);
            kafkaTemplate.send(topic, message);
        }
    }
}

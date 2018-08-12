package com.demir.core;


import com.demir.core.kafka.QueueConsumer;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTest.class);

    private static String RECEIVER_TOPIC = "receiver.t";

    @Autowired
    private QueueConsumer receiver;

    private KafkaTemplate<String, String> template;

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, RECEIVER_TOPIC);

    @Before
    public void setUp() throws Exception {
        Map<String, Object> senderProperties = KafkaTestUtils.senderProps(embeddedKafka.getBrokersAsString());
        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<String, String>(senderProperties);
        template = new KafkaTemplate<>(producerFactory);
        template.setDefaultTopic(RECEIVER_TOPIC);
    }

    @Test
    public void testReceive() throws Exception {
        String greeting = "murat.demir";
        template.sendDefault(greeting);
        LOGGER.debug("test-sender sent message='{}'", greeting);
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }


}

package com.springboot.bootcamp.Configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DynamicKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(DynamicKafkaConsumer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final TopicConfig topicConfig;

    public DynamicKafkaConsumer(KafkaTemplate<String, String> kafkaTemplate, TopicConfig topicConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicConfig = topicConfig;
    }

    /**
     * Subscribe ke topic tertentu
     */
    public void subscribeToTopic(String topic) {
        kafkaTemplate.send(topic, "Subscribed to: " + topic);
        logger.info("Subscribed to topic: {}", topic);
    }

    /**
     * Kafka Listener dengan topic yang diambil secara dinamis dari TopicConfig
     */
    @KafkaListener(
            topics = "#{@topicConfig.getTopics()}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(String message) {
        logger.info("Received message: {}", message);
    }

    /**
     * Mengecek daftar topik yang sedang aktif
     */
    public void printActiveTopics() {
        List<String> topics = topicConfig.getTopics();
        logger.info("Listening to topics: {}", topics);
    }
}

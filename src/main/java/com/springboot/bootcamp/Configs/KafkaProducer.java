package com.springboot.bootcamp.Configs;

import com.springboot.bootcamp.models.WeatherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
//    public  void sendWeatherData(String topic, String message) {
//        kafkaTemplate.send(topic, message);
//    }


    public  void sendWeatherData(String topic, String key, String message) {
        kafkaTemplate.send(topic, key, message);
    }
}

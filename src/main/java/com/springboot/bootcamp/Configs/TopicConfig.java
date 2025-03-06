package com.springboot.bootcamp.Configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TopicConfig {

    @Value("${kafka.topics}")
    private String topics;

    public List<String> getTopics() {
        return Arrays.asList(topics.split(","));
    }
}

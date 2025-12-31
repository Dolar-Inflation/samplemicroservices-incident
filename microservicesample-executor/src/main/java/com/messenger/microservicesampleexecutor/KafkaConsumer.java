package com.messenger.microservicesampleexecutor;


import com.messenger.microservicesampleexecutor.DTO.UserExecDTO;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.CompletableFuture;

@Service
@KafkaListener(topics = "my-topic", groupId = "my-group")
public class KafkaConsumer {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public KafkaConsumer(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Async
    @KafkaHandler
    public void consumer(UserExecDTO userDTO) {
        userDTO = objectMapper.convertValue(userDTO, UserExecDTO.class);

        if (userDTO.getFirstName() == "") {
            userDTO.setFirstName("default first name");
        }


        if (userDTO.getLastName() == "") {
            userDTO.setLastName("default last name");
        }

        restTemplate.postForEntity("http://microservicesample-saver/save", userDTO, Void.class);

    }



}

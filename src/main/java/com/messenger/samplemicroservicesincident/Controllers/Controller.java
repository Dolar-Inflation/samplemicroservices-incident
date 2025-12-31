package com.messenger.samplemicroservicesincident.Controllers;

import com.messenger.samplemicroservicesincident.DTO.UserDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

private final KafkaTemplate<String, UserDTO> kafkaTemplate;
static final String TOPIC="my-topic";

    public Controller(KafkaTemplate<String, UserDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/send")
    public UserDTO post(@RequestBody UserDTO userDTO) {
        kafkaTemplate.send(TOPIC, userDTO);

        return userDTO;
    }

}

package com.messenger.microservicesamplesaver.Controller;

import com.messenger.microservicesamplesaver.DTO.UserDTO;
import com.messenger.microservicesamplesaver.Entity.Users;
import com.messenger.microservicesamplesaver.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final UserRepository userRepository;

    public Controller(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody UserDTO userDTO) {
        Users users = new Users();
        users.setFirstName(userDTO.getFirstName());
        users.setLastName(userDTO.getLastName());
        userRepository.save(users);
        System.out.println(users+"всё сработало");
        return ResponseEntity.ok().build();

    }

}

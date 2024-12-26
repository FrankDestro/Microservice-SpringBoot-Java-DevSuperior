package com.devsuperior.hr_user.controllers;

import com.devsuperior.hr_user.models.entities.dto.UserDTO;
import com.devsuperior.hr_user.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO dto = userService.findUserById(id);
        log.info("teste");
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<UserDTO> findByEmail(@RequestParam String email) {
        UserDTO dto = userService.findUserByEmail(email);
        log.info("{} Received request user-service");
        return ResponseEntity.ok().body(dto);
    }
}

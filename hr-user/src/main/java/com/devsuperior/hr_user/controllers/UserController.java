package com.devsuperior.hr_user.controllers;

import com.devsuperior.hr_user.models.entities.dto.UserDTO;
import com.devsuperior.hr_user.services.UserService;
import lombok.extern.slf4j.Slf4j;
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
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<UserDTO> findByEmail(@RequestParam String email) {
        UserDTO dto = userService.findUserByEmail(email);
        return ResponseEntity.ok().body(dto);
    }
}

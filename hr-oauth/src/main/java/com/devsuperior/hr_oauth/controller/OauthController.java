package com.devsuperior.hr_oauth.controller;

import com.devsuperior.hr_oauth.models.entities.User;
import com.devsuperior.hr_oauth.services.OauthService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/users")
public class OauthController {

    @Autowired
    private OauthService oauthService;

    @GetMapping(value = "/search")
    @CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "findByEmailAlternativeWay")
    public ResponseEntity<User> findByEmail(@RequestParam String email) {
        User user = oauthService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> findByEmailAlternativeWay (String email, Throwable throwable) {
        log.error(throwable.getMessage());
        User user = new User();
        return ResponseEntity.ok(user);
    }
}



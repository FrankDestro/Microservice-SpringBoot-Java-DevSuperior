package com.devsuperior.hr_oauth.services;

import com.devsuperior.hr_oauth.integration.UserFeignClient;
import com.devsuperior.hr_oauth.models.entities.User;
import com.devsuperior.hr_oauth.services.exceptions.ResourceNotFoundException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OauthService {

    @Autowired
    private UserFeignClient userFeignClient;

    public User findByEmail(String email) {
        User user = userFeignClient.findByEmail(email).getBody();
        if (user == null) {
            log.error("Email not found: {}", email);
            throw new ResourceNotFoundException("Email not found: " + email);
        }
        log.info("Email found: {}", email);
        return user;
    }
}

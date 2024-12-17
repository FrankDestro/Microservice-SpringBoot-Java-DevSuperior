package com.devsuperior.hr_api_gateway_springcloud.controllers;

import com.devsuperior.hr_api_gateway_springcloud.models.entities.PublicKeyDTO;
import com.devsuperior.hr_api_gateway_springcloud.models.entities.PublicKeyResponse;
import com.devsuperior.hr_api_gateway_springcloud.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/keys")
    public ResponseEntity<PublicKeyResponse> getKeys() {
        List<PublicKeyDTO> list = redisService.getPublicKeys("keyPublic");
        PublicKeyResponse publicKeyResponse = new PublicKeyResponse(list);
        return new ResponseEntity<>(publicKeyResponse, HttpStatus.OK);
    }
}


package com.devsuperior.hr_api_gateway_springcloud.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller(it will not work)
public class OrderController {

    @PreAuthorize("hasAuthority('SCOPE_order:read')")
    @GetMapping(value = "/order-status")
    public String orderStatus() {
        return "Its Working";
    }
}

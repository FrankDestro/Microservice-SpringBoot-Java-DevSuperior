package com.devsuperior.hr_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableDiscoveryClient
@SpringBootApplication
public class HrUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrUserApplication.class, args);
    }
}

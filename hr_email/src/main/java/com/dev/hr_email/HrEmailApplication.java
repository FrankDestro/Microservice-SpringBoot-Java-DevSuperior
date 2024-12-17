package com.dev.hr_email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableDiscoveryClient
@EnableKafka
public class HrEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrEmailApplication.class, args);
	}

}

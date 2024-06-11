package com.devsuperior.hrpayroll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.Executor;

@Configuration
public class AppConfig {

    @Bean
    public WebClient getWebClient() {
        return WebClient.create();
    }

    @Bean
    public RestTemplate geRestTemplate() {
        return new RestTemplate();
    }
}

package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.models.entities.Payment;
import com.devsuperior.hrpayroll.models.entities.Worker;
import com.devsuperior.hrpayroll.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String workerHost;

    private final WebClient webClient;

    public Mono<Payment> getPayment(long workerId, int days) {

        try {
            return this.webClient.get()
                    .uri(workerHost + "/workers/{id}", workerId)
                    .retrieve()
                    .bodyToMono(Worker.class)
                    .map(worker -> new Payment(worker.getName(), worker.getDailyIncome(), days))
                    .onErrorMap(WebClientResponseException.NotFound.class, e -> new ResourceNotFoundException("Worker not found with ID: " + workerId));

        } catch (WebClientResponseException.NotFound e) {
            throw new ResourceNotFoundException("Worker not found with ID: " + workerId);
        }
    }
}



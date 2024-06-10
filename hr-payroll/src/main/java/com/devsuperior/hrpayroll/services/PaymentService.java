package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.models.entities.Payment;
import com.devsuperior.hrpayroll.models.entities.Worker;
import com.devsuperior.hrpayroll.services.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String workerHost;

    private final WebClient webClient;

    public Mono<Payment> getPayment(long workerId, int days) {
        return this.webClient.get()
                .uri(workerHost + "/workers/{id}", workerId)
                .retrieve()
                .bodyToMono(Worker.class)
                .map(worker -> new Payment(worker.getName(), worker.getDailyIncome(), days))
                .switchIfEmpty(Mono.error(new NotFoundException("Worker not found")));
    }
}

//    public Payment getPayment(long workerId, int days) {
//        Map<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("id", String.valueOf(workerId));
//
//        Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);
//
//        return new Payment(worker.getName(), worker.getDailyIncome(), days);
//    }


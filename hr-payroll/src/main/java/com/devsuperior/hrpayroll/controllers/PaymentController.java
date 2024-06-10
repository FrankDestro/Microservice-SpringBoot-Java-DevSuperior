package com.devsuperior.hrpayroll.controllers;

import com.devsuperior.hrpayroll.models.entities.Payment;
import com.devsuperior.hrpayroll.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

//    @GetMapping(value = "/{workerId}/days/{days}")
//    public ResponseEntity <Payment> getPayment (@PathVariable Long workerId, @PathVariable Integer days) {
//        Payment payment = paymentService.getPayment(workerId, days);
//        return ResponseEntity.ok().body(payment);
//    }

    @GetMapping(value = "/{workerId}/days/{days}")
    public Mono<ResponseEntity<Payment>> getPayment(@PathVariable Long workerId, @PathVariable Integer days) {
        return paymentService.getPayment(workerId, days)
                .map(payment -> ResponseEntity.ok(payment))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

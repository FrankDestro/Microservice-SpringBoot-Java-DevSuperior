package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.integration.WorkerFeignClient;
import com.devsuperior.hrpayroll.models.entities.Payment;
import com.devsuperior.hrpayroll.models.entities.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(long workerId, int days) {
        Worker worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}



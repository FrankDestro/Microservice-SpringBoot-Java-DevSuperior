package com.devsuperior.hrworker.models.entities.dto;


import com.devsuperior.hrworker.models.entities.Worker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class WorkerDTO {

    private Long id;
    private String name;
    private Double dailyIncome;

    public WorkerDTO(Worker workerEntity) {
        id = workerEntity.getId();
        name = workerEntity.getName();
        dailyIncome = workerEntity.getDailyIncome();
    }
}

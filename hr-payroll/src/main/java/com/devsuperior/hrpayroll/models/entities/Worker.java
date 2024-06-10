package com.devsuperior.hrpayroll.models.entities;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Worker implements Serializable {

    private Long id;
    private String name;
    private Double dailyIncome;

}

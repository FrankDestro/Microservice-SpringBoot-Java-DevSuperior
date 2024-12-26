package com.dev.hr_email.models.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class PaymentDTO {

    private String name;
    private Double dailyIncome;
    private Integer days;

    @JsonIgnore
    public double getTotal() {
        return days * dailyIncome;
    }
}

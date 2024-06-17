package com.devsuperior.hr_oauth.models.entities;

import lombok.*;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Role implements Serializable {

    private Long id;
    private String authority;
}

package com.devsuperior.hr_api_gateway_springcloud.models.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicKeyDTO {

    private String kty;
    private String e;
    private String kid;
    private String n;
}

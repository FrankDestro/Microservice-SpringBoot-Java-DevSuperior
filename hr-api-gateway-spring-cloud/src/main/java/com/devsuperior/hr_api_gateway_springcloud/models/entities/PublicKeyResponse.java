package com.devsuperior.hr_api_gateway_springcloud.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicKeyResponse {

    private List<PublicKeyDTO> keys;
}

package com.devsuperior.hr_api_gateway_springcloud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.server.ServerWebExchange;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(csrfSpec -> csrfSpec.disable())
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/hr-oauth/oauth2/token").permitAll()
                        .pathMatchers("/hr-oauth/users/search").hasAnyAuthority("ROLE_ADMIN")
                        .anyExchange().authenticated()

                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtDecoder(jwtDecoder()))
                );

        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        String jwkSetUri = "http://localhost:8083/oauth2/jwks";
        return NimbusReactiveJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }
}


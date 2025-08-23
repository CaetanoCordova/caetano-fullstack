package com.senac.AulaFullstack.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info()
                .title("Gerenciador de Contas a Pagar")
                .version("1.0.0")
                .description("Escopo da API de Caetano Córdova em Fullstack")
        );
    }
}

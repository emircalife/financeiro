package com.calife.financeiro.core;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Financeiro Pessoal")
                        .version("1.0")
                        .description("Documentação da API Financeiro Pessoal com Swagger UI"));
    }
}

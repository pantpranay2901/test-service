package com.company.servicename.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ServiceName API")
                        .version("1.0.0")
                        .description("Production-ready Spring Boot microservice boilerplate API"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local development"),
                        new Server().url("https://api.example.com").description("Production")
                ));
    }
}

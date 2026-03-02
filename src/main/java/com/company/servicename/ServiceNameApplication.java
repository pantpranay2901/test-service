package com.company.servicename;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ServiceNameApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceNameApplication.class, args);
    }
}

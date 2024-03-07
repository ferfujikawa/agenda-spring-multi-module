package com.agendaspring.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.agendaspring")
@EntityScan(basePackages = "com.agendaspring.dominio.entities")
@EnableJpaRepositories("com.agendaspring.infra.repositories")
public class AgendaSpringMultiModuleApiApplication {

    public static void main(String[] args) {
		SpringApplication.run(AgendaSpringMultiModuleApiApplication.class, args);
	}

}
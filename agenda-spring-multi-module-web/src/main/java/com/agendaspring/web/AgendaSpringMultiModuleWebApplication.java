package com.agendaspring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.agendaspring")
@EntityScan(basePackages = "com.agendaspring.dominio.entities")
@EnableJpaRepositories("com.agendaspring.infra.repositories")
public class AgendaSpringMultiModuleWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaSpringMultiModuleWebApplication.class, args);
	}

}

package com.agendaspring.thymeleaf.configs;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiRestTemplate extends RestTemplate {

  public ApiRestTemplate() {

    this.getInterceptors().add(new ApiTokenInterceptor());
  }
}

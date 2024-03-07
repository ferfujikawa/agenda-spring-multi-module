package com.agendaspring.thymeleaf.configs;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ApiTokenInterceptor implements ClientHttpRequestInterceptor {
  
  private final String AUTH_TYPE = "Bearer";

  @Override
  public ClientHttpResponse intercept(
    HttpRequest request, 
    byte[] body, 
    ClientHttpRequestExecution execution)
    throws IOException {

    String apiToken = getSessionApiToken();
    request.getHeaders().set(HttpHeaders.AUTHORIZATION, AUTH_TYPE + " " + apiToken);
    return execution.execute(request, body);
  }

  private String getSessionApiToken() {

    HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    HttpSession httpSession = httpServletRequest.getSession(false);
    return httpSession.getAttribute("apiToken").toString();
  }
}

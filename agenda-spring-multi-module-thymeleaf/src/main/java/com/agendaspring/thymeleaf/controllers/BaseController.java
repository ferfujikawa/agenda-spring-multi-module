package com.agendaspring.thymeleaf.controllers;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseController {

    @Value("${api.url}")
    private String apiUrl;

    protected String getApiUrl() {
        return apiUrl;
    }
}

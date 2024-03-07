package com.agendaspring.thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class LoginController {

    @GetMapping(path = "/login", produces = "text/html")
    public String exibirFormularioDeLogin() {

        return "login/index";
    }
}

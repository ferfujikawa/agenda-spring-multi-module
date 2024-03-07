package com.agendaspring.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@SecurityRequirement(name = "bearer-key")
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> exibirBoasVindas() {
        
        return ResponseEntity.ok("Bem vindo à API de Agenda Spring!");
    }
    
}

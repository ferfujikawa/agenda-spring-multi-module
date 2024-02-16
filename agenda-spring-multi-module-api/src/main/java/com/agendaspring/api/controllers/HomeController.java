package com.agendaspring.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<String> exibirBoasVindas() {
        
        return ResponseEntity.ok("Bem vindo à API de Agenda Spring!");
    }
    
}

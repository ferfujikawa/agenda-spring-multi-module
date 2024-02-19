package com.agendaspring.api.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agendaspring.dominio.dtos.EfetuarLoginDTO;

@Controller
@RequestMapping("login")
public class AutenticacaoController {

    private AuthenticationManager manager;

    public AutenticacaoController(AuthenticationManager manager) {
        this.manager = manager;
    }

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid EfetuarLoginDTO efetuarLoginDTO) {

        Authentication token = new UsernamePasswordAuthenticationToken(efetuarLoginDTO.getLogin(), efetuarLoginDTO.getSenha());
        Authentication authenticate = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}

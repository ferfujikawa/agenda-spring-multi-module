package com.agendaspring.thymeleaf.services;

import org.springframework.http.ResponseEntity;

import com.agendaspring.thymeleaf.dtos.CadastroTarefaDTO;

public interface ITarefaApiService {

    ResponseEntity<?> cadastrarTarefa(CadastroTarefaDTO cadastroTarefaDTO);
}

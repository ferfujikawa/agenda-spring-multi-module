package com.agendaspring.thymeleaf.services;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.agendaspring.thymeleaf.dtos.CadastroTarefaDTO;
import com.agendaspring.thymeleaf.dtos.HistoricoTarefaDTO;
import com.agendaspring.thymeleaf.dtos.ListaPaginadaDTO;

public interface ITarefaApiService {

    ResponseEntity<?> cadastrarTarefa(CadastroTarefaDTO cadastroTarefaDTO);

    ListaPaginadaDTO<HistoricoTarefaDTO> listarHistoricoDeTarefa(UUID tarefaId, int pagina, int tamanhoPagina);
}

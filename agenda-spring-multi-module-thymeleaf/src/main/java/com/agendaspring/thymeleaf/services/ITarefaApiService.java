package com.agendaspring.thymeleaf.services;

import java.util.UUID;

import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.agendaspring.thymeleaf.dtos.CadastroTarefaDTO;
import com.agendaspring.thymeleaf.dtos.HistoricoTarefaDTO;
import com.agendaspring.thymeleaf.dtos.ListaPaginadaDTO;
import com.agendaspring.thymeleaf.dtos.RegistrarAnotacaoTarefaDTO;
import com.agendaspring.thymeleaf.dtos.TarefaDTO;

public interface ITarefaApiService {

    TarefaDTO cadastrarTarefa(CadastroTarefaDTO cadastroTarefaDTO) throws BadRequest;

    ListaPaginadaDTO<HistoricoTarefaDTO> listarHistoricoDeTarefa(UUID tarefaId, int pagina, int tamanhoPagina);

    Boolean registrarAnotacao(UUID tarefaId, RegistrarAnotacaoTarefaDTO registroAnotacao);

    Boolean cancelar(UUID tarefaId, RegistrarAnotacaoTarefaDTO registroAnotacao);

    Boolean marcarTarefaVisualizada(UUID tarefaId);

    Boolean marcarTarefaNaoVisualizada(UUID tarefaId);
}

package com.agendaspring.dominio.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agendaspring.dominio.dtos.AlteracaoPrazoTarefaDTO;
import com.agendaspring.dominio.dtos.CadastroTarefaDTO;
import com.agendaspring.dominio.dtos.RegistrarAnotacaoTarefaDTO;
import com.agendaspring.dominio.entities.HistoricoTarefa;
import com.agendaspring.dominio.entities.Tarefa;

public interface ITarefaService {

    Page<Tarefa> listarTarefas(Pageable paginacao);

    Tarefa cadastrarTarefa(CadastroTarefaDTO tarefa);

    Boolean existeTarefaComMesmoPrazo(LocalDateTime horario);

    Boolean marcarTarefaVisualizada(UUID id);

    Boolean marcarTarefaNaoVisualizada(UUID id);

    Page<HistoricoTarefa> listarHistoricosDeTarefa(UUID tarefaId, Pageable paginacao);

    Boolean registrarAnotacao(UUID id, RegistrarAnotacaoTarefaDTO registroAnotacao);

    Boolean darAndamento(UUID id, RegistrarAnotacaoTarefaDTO registroAnotacao);

    Boolean cancelar(UUID id, RegistrarAnotacaoTarefaDTO registroAnotacao);

    Boolean concluir(UUID id, RegistrarAnotacaoTarefaDTO registroAnotacao);

    Boolean alterarPrazoTarefa(UUID id, AlteracaoPrazoTarefaDTO dto);
}
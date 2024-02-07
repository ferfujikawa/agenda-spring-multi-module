package com.agendaspring.infra.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agendaspring.dominio.dtos.CadastroTarefaDTO;
import com.agendaspring.dominio.entities.Tarefa;

public interface ITarefaService {

    Page<Tarefa> listarTarefas(Pageable paginacao);

    Tarefa cadastrarTarefa(CadastroTarefaDTO tarefa);
}
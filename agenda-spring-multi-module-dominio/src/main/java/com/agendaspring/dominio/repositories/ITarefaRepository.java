package com.agendaspring.dominio.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agendaspring.dominio.entities.Tarefa;

public interface ITarefaRepository {

    Page<Tarefa> listarTarefas(Pageable paginacao);

    Tarefa save(Tarefa entity);

    Boolean existeTarefaComMesmoPrazo(LocalDateTime horario);

    Optional<Tarefa> findById(Long id);
}
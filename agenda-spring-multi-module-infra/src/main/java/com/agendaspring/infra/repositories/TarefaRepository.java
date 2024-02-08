package com.agendaspring.infra.repositories;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agendaspring.dominio.entities.Tarefa;
import com.agendaspring.dominio.repositories.ITarefaRepository;

@Repository
public interface TarefaRepository extends ITarefaRepository, JpaRepository<Tarefa, UUID> {

    @Override
    @Query("SELECT t FROM Tarefa t")
    Page<Tarefa> listarTarefas(Pageable paginacao);

    @Override
    @Query("SELECT CASE WHEN count(t) > 0 THEN true ELSE false END FROM Tarefa t WHERE prazo.valor = :horario")
    Boolean existeTarefaComMesmoPrazo(LocalDateTime horario);
}

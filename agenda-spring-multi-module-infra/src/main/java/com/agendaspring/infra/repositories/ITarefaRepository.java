package com.agendaspring.infra.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agendaspring.dominio.entities.Tarefa;

@Repository
public interface ITarefaRepository extends JpaRepository<Tarefa, UUID> {

    @Query("SELECT t FROM Tarefa t")
    Page<Tarefa> listarTarefas(Pageable paginacao);
}
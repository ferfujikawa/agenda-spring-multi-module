package com.agendaspring.infra.services;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agendaspring.dominio.dtos.CadastroTarefaDTO;
import com.agendaspring.dominio.entities.Tarefa;
import com.agendaspring.infra.repositories.ITarefaRepository;

@Service
public class TarefaService implements ITarefaService {

    private ITarefaRepository repositorio;

    public TarefaService(ITarefaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Page<Tarefa> listarTarefas(Pageable paginacao) {
        
        return repositorio.listarTarefas(paginacao);
    }

    @Override
    @Transactional
    public Tarefa cadastrarTarefa(CadastroTarefaDTO tarefa) {
        
        Tarefa tarefaCadastrada = repositorio.save(new Tarefa(tarefa.getTitulo(), tarefa.getPrazo(), tarefa.getAnotacao()));
		return tarefaCadastrada;
    }
}

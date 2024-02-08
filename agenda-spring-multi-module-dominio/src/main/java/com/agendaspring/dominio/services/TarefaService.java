package com.agendaspring.dominio.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agendaspring.dominio.dtos.CadastroTarefaDTO;
import com.agendaspring.dominio.dtos.RegistrarAnotacaoTarefaDTO;
import com.agendaspring.dominio.entities.HistoricoTarefa;
import com.agendaspring.dominio.entities.Tarefa;
import com.agendaspring.dominio.repositories.ITarefaRepository;

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

    @Override
    public Boolean existeTarefaComMesmoPrazo(LocalDateTime horario) {
        
        return repositorio.existeTarefaComMesmoPrazo(horario);
    }

    @Override
    @Transactional
    public Boolean marcarTarefaVisualizada(UUID id) {
        
        Optional<Tarefa> tarefa = repositorio.findById(id);
		if (tarefa.isPresent()) {
			tarefa.get().marcarComoVisualizada(null);
			return true;
		}
		
		return null;
    }

    @Override
    @Transactional
    public Boolean marcarTarefaNaoVisualizada(UUID id) {
        
        Optional<Tarefa> tarefa = repositorio.findById(id);
		if (tarefa.isPresent()) {
			tarefa.get().marcarComoNaoVisualizada(null);
			return true;
		}
		
		return null;
    }

    @Override
    public Page<HistoricoTarefa> listarHistoricosDeTarefa(UUID tarefaId, Pageable paginacao) {
        
        return repositorio.listarHistoricosDeTarefa(tarefaId, paginacao);
    }

    @Override
    @Transactional
    public Boolean registrarAnotacao(UUID id, RegistrarAnotacaoTarefaDTO registroAnotacao) {
        
        Optional<Tarefa> tarefa = repositorio.findById(id);
		
		if (tarefa.isPresent()) {
			tarefa.get().registrarAnotacao(registroAnotacao.getAnotacao());
			return true;
		}
		
		return false;
    }

    @Override
    @Transactional
    public Boolean darAndamento(UUID id, RegistrarAnotacaoTarefaDTO registroAnotacao) {
        
        Optional<Tarefa> tarefa = repositorio.findById(id);
		
		if (tarefa.isPresent()) {
			tarefa.get().darAndamento(registroAnotacao.getAnotacao());
			return true;
		}
		
		return false;
    }

    @Override
    @Transactional
    public Boolean cancelar(UUID id, RegistrarAnotacaoTarefaDTO registroAnotacao) {
        
        Optional<Tarefa> tarefa = repositorio.findById(id);
		
		if (tarefa.isPresent()) {
			tarefa.get().cancelar(registroAnotacao.getAnotacao());
			return true;
		}
		
		return false;
    }
}

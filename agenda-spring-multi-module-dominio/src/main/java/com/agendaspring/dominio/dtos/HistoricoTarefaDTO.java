package com.agendaspring.dominio.dtos;

import java.time.LocalDateTime;

import com.agendaspring.dominio.entities.HistoricoTarefa;

public class HistoricoTarefaDTO {

	private LocalDateTime dataHistorico;
	private String anotacao;
	private String evento;
	
	public HistoricoTarefaDTO(LocalDateTime dataHistorico, String anotacao, String evento) {
		this.dataHistorico = dataHistorico;
		this.anotacao = anotacao;
		this.evento = evento;
	}

    public HistoricoTarefaDTO(HistoricoTarefa historicoTarefa) {
        this(historicoTarefa.getDataHistorico(),
            historicoTarefa.getAnotacao(),
            historicoTarefa.getEvento());
    }

	public LocalDateTime getDataHistorico() {
		return dataHistorico;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public String getEvento() {
		return evento;
	}

	public void setDataHistorico(LocalDateTime dataHistorico) {
		this.dataHistorico = dataHistorico;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}
}

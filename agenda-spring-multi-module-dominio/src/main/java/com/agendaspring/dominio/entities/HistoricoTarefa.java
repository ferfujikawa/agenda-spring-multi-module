package com.agendaspring.dominio.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "historicos_tarefa")
public class HistoricoTarefa {
	
	@Id
    @Column(name = "id")
	private UUID id;
	
    @Column(name = "data_historico")
	private LocalDateTime dataHistorico;
	
    @Column(name = "anotacao")
	private String anotacao;
	
    @Column(name = "evento")
	private String evento;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "tarefa_id")
    private Tarefa tarefa;
	
	protected HistoricoTarefa() {}

	public HistoricoTarefa(Tarefa tarefa, String anotacao, String evento) {
        this.id = UUID.randomUUID();
        this.tarefa = tarefa;
		this.anotacao = anotacao;
		this.evento = evento;
		dataHistorico = LocalDateTime.now();
	}

	public UUID getId() {
		return id;
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
}

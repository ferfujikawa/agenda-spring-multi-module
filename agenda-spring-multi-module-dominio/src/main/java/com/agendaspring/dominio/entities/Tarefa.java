package com.agendaspring.dominio.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.agendaspring.dominio.valueobjects.PrazoTarefa;
import com.agendaspring.dominio.valueobjects.SituacaoTarefa;

public class Tarefa {

    private UUID id;
    private String titulo;
    private LocalDateTime dataCadastro;
    private Boolean visualizada;
    private SituacaoTarefa situacao;
    private PrazoTarefa prazo;

    protected Tarefa() {}

    public Tarefa(String titulo, LocalDateTime prazo, String anotacao) {
		
        this.id = UUID.randomUUID();
        this.titulo = titulo;
		this.prazo = new PrazoTarefa(prazo);
		this.visualizada = false;
		dataCadastro = LocalDateTime.now();
		situacao = new SituacaoTarefa();
	}

    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public Boolean getVisualizada() {
        return visualizada;
    }

    public SituacaoTarefa getSituacao() {
        return situacao;
    }

    public PrazoTarefa getPrazo() {
        return prazo;
    }    
}

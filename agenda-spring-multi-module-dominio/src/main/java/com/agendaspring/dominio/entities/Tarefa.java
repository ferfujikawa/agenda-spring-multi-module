package com.agendaspring.dominio.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Embedded;

import com.agendaspring.dominio.valueobjects.PrazoTarefa;
import com.agendaspring.dominio.valueobjects.SituacaoTarefa;

@Entity
@Table(name = "tarefas")
public class Tarefa {
    
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Column(name = "visualizada")
    private Boolean visualizada;

    @Embedded
    private SituacaoTarefa situacao;

    @Embedded
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

    public Boolean podeAlterarPrazo() {
        // TODO Implementar regra de negócio deste método
        return true;
    }

    public Boolean podeCancelar() {
        // TODO Implementar regra de negócio deste método
        return true;
    }

    public Boolean podeConcluir() {
        // TODO Implementar regra de negócio deste método
        return true;
    }    
}

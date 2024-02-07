package com.agendaspring.dominio.dtos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.agendaspring.dominio.entities.Tarefa;

public class TarefaDTO {

    private UUID id;
	private String titulo;
	private LocalDateTime dataCadastro;
	private Boolean visualizada;
	private String situacao;
	private LocalDateTime prazo;
	private Long horasParaPrazo;

    protected TarefaDTO() {}
	
	public TarefaDTO(UUID id, String titulo, LocalDateTime dataCadastro, Boolean visualizada, String situacao,
			LocalDateTime prazo) {
		this.id = id;
		this.titulo = titulo;
		this.dataCadastro = dataCadastro;
		this.visualizada = visualizada;
		this.situacao = situacao;
		this.prazo = prazo;
		
		LocalDateTime horaAtual = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
		this.horasParaPrazo = horaAtual.until(this.prazo, ChronoUnit.HOURS); 
	}

	public TarefaDTO(Tarefa tarefa) {
		this(tarefa.getId(),
			tarefa.getTitulo(),
			tarefa.getDataCadastro(),
			tarefa.getVisualizada(),
			tarefa.getSituacao().getValor().getDescricao(),
			tarefa.getPrazo().getValor());
	}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getVisualizada() {
        return visualizada;
    }

    public void setVisualizada(Boolean visualizada) {
        this.visualizada = visualizada;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public LocalDateTime getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDateTime prazo) {
        this.prazo = prazo;
    }

    public Long getHorasParaPrazo() {
        return horasParaPrazo;
    }

    public void setHorasParaPrazo(Long horasParaPrazo) {
        this.horasParaPrazo = horasParaPrazo;
    }
}

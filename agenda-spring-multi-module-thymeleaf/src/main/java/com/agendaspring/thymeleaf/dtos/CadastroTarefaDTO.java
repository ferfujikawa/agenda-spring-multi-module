package com.agendaspring.thymeleaf.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class CadastroTarefaDTO {
			
	@NotBlank(message = "É necessário informar um título para a tarefa")
	private String titulo;
	
	@NotNull(message = "É necessário informar um prazo para a tarefa")
	@FutureOrPresent(message = "O prazo da tarefa não pode ser retroativo")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime prazo;
	
	private String anotacao;

	public CadastroTarefaDTO(String titulo, LocalDateTime prazo, String anotacao) {
		this.titulo = titulo;
		this.prazo = prazo;
		this.anotacao = anotacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getPrazo() {
		return prazo;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setPrazo(LocalDateTime prazo) {
		this.prazo = prazo;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}
}
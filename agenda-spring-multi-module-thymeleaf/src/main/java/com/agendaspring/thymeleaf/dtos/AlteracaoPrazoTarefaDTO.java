package com.agendaspring.thymeleaf.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class AlteracaoPrazoTarefaDTO {
			
	@NotNull(message = "É necessário informar um prazo para a tarefa")
	@FutureOrPresent(message = "O prazo da tarefa não pode ser retroativo")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime prazo;
	
	private String anotacao;
	
	public AlteracaoPrazoTarefaDTO(LocalDateTime prazo, String anotacao) {
		this.prazo = prazo;
		this.anotacao = anotacao;
	}

	public LocalDateTime getPrazo() {
		return prazo;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public void setPrazo(LocalDateTime prazo) {
		this.prazo = prazo;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}
}

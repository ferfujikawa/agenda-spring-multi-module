package com.agendaspring.dominio.dtos;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class RegistrarAnotacaoTarefaDTO {

	@NotBlank(message = "Informe uma anotação")
	@Length(min = 3, message = "A anotação precisa ter pelo menos 3 caracteres")
	private String anotacao;

	public String getAnotacao() {
		return anotacao;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}
}

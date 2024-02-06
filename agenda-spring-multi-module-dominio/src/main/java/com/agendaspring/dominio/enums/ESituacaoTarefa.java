package com.agendaspring.dominio.enums;

public enum ESituacaoTarefa {

    ABERTA("Aberta"),
	EM_ANDAMENTO("Em Andamento"),
	CONCLUIDA("Concluída"),
	CANCELADA("Cancelada");

    private String descricao;

    private ESituacaoTarefa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}

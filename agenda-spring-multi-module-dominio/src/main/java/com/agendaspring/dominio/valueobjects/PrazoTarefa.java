package com.agendaspring.dominio.valueobjects;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PrazoTarefa {

    @Column(name = "prazo")
    private LocalDateTime valor;

    public PrazoTarefa(LocalDateTime prazoInicial) {
		this.valor = prazoInicial.truncatedTo(ChronoUnit.HOURS);
	}

    public LocalDateTime getValor() {
        return valor;
    }
}

package com.agendaspring.dominio.valueobjects;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PrazoTarefa {

    private LocalDateTime valor;

    public PrazoTarefa(LocalDateTime prazoInicial) {
		this.valor = prazoInicial.truncatedTo(ChronoUnit.HOURS);
	}

    public LocalDateTime getValor() {
        return valor;
    }
}

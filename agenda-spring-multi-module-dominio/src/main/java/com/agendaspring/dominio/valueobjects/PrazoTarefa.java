package com.agendaspring.dominio.valueobjects;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PrazoTarefa {

    @Column(name = "prazo")
    private LocalDateTime valor;

    protected PrazoTarefa() {}

    public PrazoTarefa(LocalDateTime prazoInicial) {
		this.valor = prazoInicial.truncatedTo(ChronoUnit.HOURS);
	}

    public LocalDateTime getValor() {
        return valor;
    }

    public Boolean alterar(LocalDateTime novoPrazo) {
		novoPrazo = novoPrazo.truncatedTo(ChronoUnit.HOURS);
		
		if (novoPrazo.isAfter(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS))) {
			this.valor = novoPrazo;
			return true;
		}
		
		return false;
	}
}

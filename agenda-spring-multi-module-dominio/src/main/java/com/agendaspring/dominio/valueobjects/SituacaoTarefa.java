package com.agendaspring.dominio.valueobjects;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

import com.agendaspring.dominio.enums.ESituacaoTarefa;
import com.agendaspring.dominio.enums.ESituacaoTarefaConverter;

@Embeddable
public class SituacaoTarefa {

    @Column(name = "situacao")
    @Convert(converter=ESituacaoTarefaConverter.class)
    private ESituacaoTarefa valor;

    public SituacaoTarefa() {

        this.valor = ESituacaoTarefa.ABERTA;
    }

    public ESituacaoTarefa getValor() {
        return valor;
    }

    public Boolean podeMarcarNaoVisualizada() {
		return this.valor == ESituacaoTarefa.ABERTA;
	}

    public boolean darAndamento() {
        
        if (this.podeDarAndamento()) {
			this.valor = ESituacaoTarefa.EM_ANDAMENTO;
			return true;
		}
		
		return false;
    }

    public Boolean cancelar() {
        
		if (this.podeCancelar()) {
			this.valor = ESituacaoTarefa.CANCELADA;
			return true;
		}
		
		return false;
	}

    public Boolean podeDarAndamento() {
        return this.valor == ESituacaoTarefa.ABERTA;
    }

    public Boolean podeCancelar() {
		return Arrays.asList(ESituacaoTarefa.ABERTA, ESituacaoTarefa.EM_ANDAMENTO).contains(this.valor);
	}
}

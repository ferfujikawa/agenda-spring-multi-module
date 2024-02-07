package com.agendaspring.dominio.valueobjects;

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
}

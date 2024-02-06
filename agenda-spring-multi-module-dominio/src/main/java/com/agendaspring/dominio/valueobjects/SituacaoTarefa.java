package com.agendaspring.dominio.valueobjects;

import com.agendaspring.dominio.enums.ESituacaoTarefa;

public class SituacaoTarefa {

    private ESituacaoTarefa valor;

    public SituacaoTarefa() {

        this.valor = ESituacaoTarefa.ABERTA;
    }

    public ESituacaoTarefa getValor() {
        return valor;
    }
}

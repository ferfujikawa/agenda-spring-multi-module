package com.agendaspring.api.controllers.errors;

import org.springframework.validation.FieldError;

public class ErroValidacao {

    private String campo;

    private String mensagem;

    public ErroValidacao(FieldError error) {
        this.campo = error.getField();
        this.mensagem = error.getDefaultMessage();
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

package com.agendaspring.dominio.dtos;

public class EfetuarLoginDTO {

    private String login;

    private String senha;

    public EfetuarLoginDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}

package com.agendaspring.thymeleaf.dtos;

import org.springframework.security.core.Authentication;

public class RealizarLoginDTO {

    private String login;

    private String senha;

    public RealizarLoginDTO(Authentication authentication) {
        this.login = authentication.getName();
        this.senha = authentication.getCredentials().toString();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

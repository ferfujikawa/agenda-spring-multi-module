package com.agendaspring.api.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.agendaspring.dominio.entities.Usuario;

public interface IAutenticacaoService extends UserDetailsService {

    Usuario obterUsuarioPeloLogin(String login);
}

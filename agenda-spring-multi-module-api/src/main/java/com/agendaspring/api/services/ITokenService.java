package com.agendaspring.api.services;

import com.agendaspring.dominio.entities.Usuario;

public interface ITokenService {

    String gerarToken(Usuario usuario);
}

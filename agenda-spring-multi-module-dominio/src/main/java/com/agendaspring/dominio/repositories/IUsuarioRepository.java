package com.agendaspring.dominio.repositories;

import com.agendaspring.dominio.entities.Usuario;

public interface IUsuarioRepository {

    Usuario findByLogin(String login);

}

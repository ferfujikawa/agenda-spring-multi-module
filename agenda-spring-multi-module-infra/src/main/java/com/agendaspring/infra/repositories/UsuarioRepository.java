package com.agendaspring.infra.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agendaspring.dominio.entities.Usuario;
import com.agendaspring.dominio.repositories.IUsuarioRepository;

@Repository
public interface UsuarioRepository extends IUsuarioRepository, JpaRepository<Usuario, UUID> {

    @Override
    @Query("SELECT u FROM Usuario u WHERE login = :login")
    Usuario findByLogin(String login);
}

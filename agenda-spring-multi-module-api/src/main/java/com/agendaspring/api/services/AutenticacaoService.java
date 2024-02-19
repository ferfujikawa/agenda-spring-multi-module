package com.agendaspring.api.services;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agendaspring.dominio.entities.Usuario;
import com.agendaspring.dominio.repositories.IUsuarioRepository;

@Service
public class AutenticacaoService implements IAutenticacaoService {

    private IUsuarioRepository usuarioRepository;

    public AutenticacaoService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario usuarioEncontrado = usuarioRepository.findByLogin(username);
        return new User(usuarioEncontrado.getLogin(), usuarioEncontrado.getSenha(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}

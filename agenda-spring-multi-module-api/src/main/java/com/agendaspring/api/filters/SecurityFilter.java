package com.agendaspring.api.filters;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.agendaspring.api.services.ITokenService;
import com.agendaspring.dominio.entities.Usuario;
import com.agendaspring.dominio.repositories.IUsuarioRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private ITokenService tokenService;
    private IUsuarioRepository usuarioRepository;

    public SecurityFilter(
        ITokenService tokenService,
        IUsuarioRepository usuarioRepository) {

        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(request);

        if (token != null) {
            String subject = tokenService.getSubject(token);

            Usuario usuarioLogado = usuarioRepository.findByLogin(subject);

            Authentication authentication = new UsernamePasswordAuthenticationToken(usuarioLogado, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        
        String cabecalho = request.getHeader("Authorization");
        if (cabecalho != null) {
            return cabecalho.replace("Bearer ", "");
        }

        return null;
    }

}

package com.agendaspring.api.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = recuperarToken(request);


        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        
        String cabecalho = request.getHeader("Authorization");
        if (cabecalho == null) {
            throw new RuntimeException("Token JWT não enviado no cabeçalho Authorization!");
        }

        return cabecalho.replace("Bearer ", "");
    }

}

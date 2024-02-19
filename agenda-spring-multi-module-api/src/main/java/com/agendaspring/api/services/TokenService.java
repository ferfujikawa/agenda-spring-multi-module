package com.agendaspring.api.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.agendaspring.dominio.entities.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Service
public class TokenService implements ITokenService {

    @Override
    public String gerarToken(Usuario usuario) {
        
        try {
            Algorithm algoritmo = Algorithm.HMAC256("12345678");
            return JWT.create()
                .withIssuer("Agenda Spring API")
                .withSubject(usuario.getLogin())
                .withExpiresAt(obterDataExpiracao())
                .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    private Instant obterDataExpiracao() {
        
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}

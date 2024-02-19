package com.agendaspring.api.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.agendaspring.dominio.entities.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService implements ITokenService {

    private String secret;

    public TokenService(@Value("${api.security.token.secret}") String secret) {
        this.secret = secret;
    }

    @Override
    public String gerarToken(Usuario usuario) {
        
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
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

    @Override
    public String getSubject(String token) {
        
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);

            return JWT.require(algoritmo)
                .withIssuer("Agenda Spring API")
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token inválido ou expirado!");
        }
    }

}

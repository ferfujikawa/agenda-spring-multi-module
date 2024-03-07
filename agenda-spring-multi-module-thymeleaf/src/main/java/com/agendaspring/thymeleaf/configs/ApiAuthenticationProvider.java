package com.agendaspring.thymeleaf.configs;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.agendaspring.thymeleaf.dtos.RealizarLoginDTO;
import com.agendaspring.thymeleaf.services.UserApiService;

@Component
public class ApiAuthenticationProvider implements AuthenticationProvider {
    
    private UserApiService userApiService;
    
    public ApiAuthenticationProvider(UserApiService userApiService) {
    
        this.userApiService = userApiService;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    
        RealizarLoginDTO realizarLoginDTO = new RealizarLoginDTO(authentication);

        String token = null;
        try {
            token = userApiService.realizarLogin(realizarLoginDTO);    
        } catch (HttpClientErrorException e) {
            throw new AuthenticationException("Usuário ou senha inválidos") {};
        }

        if (token == null) {
            throw new AuthenticationException("Usuário ou senha inválidos") {};
        }
        
        return new UsernamePasswordAuthenticationToken(token, null, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

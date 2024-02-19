package com.agendaspring.api.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agendaspring.api.services.IAutenticacaoService;
import com.agendaspring.api.services.ITokenService;
import com.agendaspring.dominio.dtos.EfetuarLoginDTO;
import com.agendaspring.dominio.dtos.TokenDTO;
import com.agendaspring.dominio.entities.Usuario;

@Controller
@RequestMapping("login")
public class AutenticacaoController {

    private AuthenticationManager manager;
    private ITokenService tokenService;
    private IAutenticacaoService autenticacaoService;

    public AutenticacaoController(
        AuthenticationManager manager,
        ITokenService tokenService,
        IAutenticacaoService autenticacaoService) {

        this.manager = manager;
        this.tokenService = tokenService;
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping
    public ResponseEntity<TokenDTO> efetuarLogin(@RequestBody @Valid EfetuarLoginDTO efetuarLoginDTO) {

        Authentication token = new UsernamePasswordAuthenticationToken(efetuarLoginDTO.getLogin(), efetuarLoginDTO.getSenha());
        Authentication authenticate = manager.authenticate(token);

        Usuario usuarioLogado = autenticacaoService.obterUsuarioPeloLogin(((UserDetails) authenticate.getPrincipal()).getUsername());

        String tokenJWT = tokenService.gerarToken(usuarioLogado);

        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }
}

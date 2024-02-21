package com.agendaspring.api.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.agendaspring.dominio.dtos.CadastroTarefaDTO;
import com.agendaspring.dominio.dtos.DataTableDTO;
import com.agendaspring.dominio.dtos.TarefaDTO;
import com.agendaspring.dominio.entities.Tarefa;
import com.agendaspring.dominio.services.ITarefaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(path = "tarefas", produces = "application/json")
@SecurityRequirement(name = "bearer-key")
public class TarefaController {

    private ITarefaService tarefaService;

    public TarefaController(ITarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
	public ResponseEntity<DataTableDTO<TarefaDTO>> listarTarefas(
			@RequestParam(name = "draw", defaultValue = "1") int draw,
			@RequestParam(name = "start", defaultValue = "1") int inicioPagina,
            @RequestParam(name = "length", defaultValue = "10") int tamanhoPagina) {
		
		int numeroPagina = inicioPagina / tamanhoPagina;
		Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina, Sort.by(Sort.Direction.ASC, "prazo.valor"));
		
        DataTableDTO<TarefaDTO> dados = new DataTableDTO<TarefaDTO>(draw, tarefaService.listarTarefas(pageable).map(t -> new TarefaDTO(t)));
		return ResponseEntity.ok(dados);
	}

    @PostMapping
	public ResponseEntity<TarefaDTO> cadastrarTarefa(
		@RequestBody @Valid CadastroTarefaDTO tarefa,
		UriComponentsBuilder uriComponentsBuilder) {
		
		Tarefa tarefaCadastrada = tarefaService.cadastrarTarefa(tarefa);
        URI uri = uriComponentsBuilder.path("/tarefas/{id}").buildAndExpand(tarefaCadastrada.getId()).toUri();

		return ResponseEntity.created(uri).body(new TarefaDTO(tarefaCadastrada));
	}
}

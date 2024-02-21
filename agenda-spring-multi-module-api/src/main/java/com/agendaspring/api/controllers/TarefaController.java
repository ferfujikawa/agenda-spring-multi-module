package com.agendaspring.api.controllers;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.agendaspring.dominio.dtos.AlteracaoPrazoTarefaDTO;
import com.agendaspring.dominio.dtos.CadastroTarefaDTO;
import com.agendaspring.dominio.dtos.DataTableDTO;
import com.agendaspring.dominio.dtos.HistoricoTarefaDTO;
import com.agendaspring.dominio.dtos.ListaPaginadaDTO;
import com.agendaspring.dominio.dtos.RegistrarAnotacaoTarefaDTO;
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

	@GetMapping("{id}/historico")
	public ResponseEntity<ListaPaginadaDTO<HistoricoTarefaDTO>> exibirHistorico(
		@PathVariable UUID id,
		@RequestParam(name = "pagina", defaultValue = "1") int pagina,
        @RequestParam(name = "tamanhoPagina", defaultValue = "10") int tamanhoPagina) {
		
		Pageable paginacao = PageRequest.of(pagina - 1, tamanhoPagina, Sort.by(Sort.Direction.DESC, "h.dataHistorico"));
		Page<HistoricoTarefaDTO> historicos = tarefaService.listarHistoricosDeTarefa(id, paginacao)
			.map(h -> new HistoricoTarefaDTO(h));
		
		ListaPaginadaDTO<HistoricoTarefaDTO> listaPaginada = new ListaPaginadaDTO<HistoricoTarefaDTO>(historicos);
		return ResponseEntity.ok().body(listaPaginada);
	}

	@PostMapping("{id}/registrar-anotacao")
	public ResponseEntity<String> registrarAnotacao(
			@PathVariable UUID id,
			@Valid @RequestBody RegistrarAnotacaoTarefaDTO registroAnotacao) {
		
		if (tarefaService.registrarAnotacao(id, registroAnotacao)) {
			return ResponseEntity.ok().body("Anotação registrada");
		}
		
		return ResponseEntity.badRequest().body("Erro ao registrar anotação");
	}

	@PutMapping("{id}/marcar-visualizada")
	public ResponseEntity<String> marcarVisualizada(@PathVariable UUID id) {
		
		if (tarefaService.marcarTarefaVisualizada(id)) {
			return ResponseEntity.ok().body("Tarefa marcada como visualizada");
		}
		
		return ResponseEntity.badRequest().body("Erro ao marcar tarefa como visualizada");
	}
	
	@PutMapping("{id}/marcar-nao-visualizada")
	public ResponseEntity<String> marcarNaoVisualizada(@PathVariable UUID id) {
		
		if (tarefaService.marcarTarefaNaoVisualizada(id)) {
			return ResponseEntity.ok().body("Tarefa marcada como não visualizada");
		}
		
		return ResponseEntity.badRequest().body("Erro ao marcar tarefa como não visualizada");
	}

	@PutMapping("{id}/dar-andamento")
	public ResponseEntity<String> darAndamento(
			@PathVariable UUID id,
			@Valid @RequestBody RegistrarAnotacaoTarefaDTO registroAnotacao) {
		
		if (tarefaService.darAndamento(id, registroAnotacao)) {
			return ResponseEntity.ok().body("Andamento de tarefa registrado");
		}
		
		return ResponseEntity.badRequest().body("Erro ao registrar andamento de tarefa");
	}

	@PutMapping("{id}/cancelar")
	public ResponseEntity<String> cancelar(
			@PathVariable UUID id,
			@Valid @RequestBody RegistrarAnotacaoTarefaDTO registroAnotacao) {
		
		if (tarefaService.cancelar(id, registroAnotacao)) {
			return ResponseEntity.ok().body("Tarefa cancelada");
		}
		
		return ResponseEntity.badRequest().body("Erro ao cancelar tarefa");
	}

	@PutMapping("{id}/concluir")
	public ResponseEntity<String> concluir(
			@PathVariable UUID id,
			@Valid @RequestBody RegistrarAnotacaoTarefaDTO registroAnotacao) {
		
		if (tarefaService.concluir(id, registroAnotacao)) {
			return ResponseEntity.ok().body("Tarefa concluída");
		}
		
		return ResponseEntity.badRequest().body("Erro ao concluir tarefa");
	}

	@PutMapping("{id}/alterar-prazo")
	public ResponseEntity<String> alterarPrazo(
			@RequestBody @Valid AlteracaoPrazoTarefaDTO tarefa,
			@PathVariable UUID id) {
		
		if (tarefaService.alterarPrazoTarefa(id, tarefa)) {
			return ResponseEntity.ok().body("Prazo de tarefa alterado");
		}

		return ResponseEntity.badRequest().body("Erro ao alterar prazo de tarefa");
	}
}

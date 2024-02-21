package com.agendaspring.thymeleaf.controllers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agendaspring.thymeleaf.dtos.AlteracaoPrazoTarefaDTO;
import com.agendaspring.thymeleaf.dtos.CadastroTarefaDTO;
import com.agendaspring.thymeleaf.dtos.HistoricoTarefaDTO;
import com.agendaspring.thymeleaf.dtos.ListaPaginadaDTO;
import com.agendaspring.thymeleaf.dtos.RegistrarAnotacaoTarefaDTO;
import com.agendaspring.thymeleaf.dtos.TarefaDTO;
import com.agendaspring.thymeleaf.services.ITarefaApiService;

@Controller
@RequestMapping("tarefas")
public class TarefaController extends BaseController {

	private ITarefaApiService tarefaApiService;

	public TarefaController(ITarefaApiService tarefaApiService) {
		
		this.tarefaApiService = tarefaApiService;
	}

	@GetMapping
	public String exibirPaginaListaTarefas(Model model) {
	
		model.addAttribute("apiUrl", this.getApiUrl());

		return "tarefas/index";
	}

  	@GetMapping("nova")
	public String exibirFormularioCadastroTarefa(@ModelAttribute("tarefa") CadastroTarefaDTO tarefa) {
		
    return "tarefas/nova";
	}

  	@GetMapping("confirmacao-cadastro")
	public String exibirConfirmacaoCadastro(@ModelAttribute("tarefa") TarefaDTO tarefa) {
		
		if (tarefa.getId() == null) {
			return "redirect:/tarefas";
		}

		return "tarefas/confirmacao-cadastro";
	}

	@GetMapping("{id}/historico")
	public String exibirHistorico(
		final Model model,
		@PathVariable UUID id,
		@RequestParam(name = "pagina", defaultValue = "1") int pagina,
        @RequestParam(name = "tamanhoPagina", defaultValue = "10") int tamanhoPagina) {
		
		ListaPaginadaDTO<HistoricoTarefaDTO> historicos = tarefaApiService.listarHistoricoDeTarefa(id, pagina, tamanhoPagina);
		model.addAttribute("tarefaId", id);
		model.addAttribute("historicos", historicos);
		
		return "tarefas/historico";
	}

	@GetMapping("{id}/registrar-anotacao")
	public String exibirFormularioRegistrarAnotacao(
			final Model model,
			@PathVariable UUID id,
			@ModelAttribute("registroAnotacao") RegistrarAnotacaoTarefaDTO registroAnotacao) {
		
		model.addAttribute("tarefaId", id);
		
		return "tarefas/registrar-anotacao";
	}

	@GetMapping("{id}/cancelar")
	public String exibirFormularioCancelar(
			final Model model,
			@PathVariable UUID id,
			@ModelAttribute("registroAnotacao") RegistrarAnotacaoTarefaDTO registroAnotacao) {
		
		model.addAttribute("tarefaId", id);
		
		return "tarefas/cancelar";
	}

	@GetMapping("{id}/concluir")
	public String exibirFormularioConcluirComAnotacao(
			final Model model,
			@PathVariable UUID id,
			@ModelAttribute("registroAnotacao") RegistrarAnotacaoTarefaDTO registroAnotacao) {
		
		model.addAttribute("tarefaId", id);
		
		return "tarefas/concluir";
	}

	@GetMapping("{id}/alterar-prazo")
	public String exibirFormularioAlterarPrazo(
			final Model model,
			@PathVariable UUID id,
			@ModelAttribute("tarefa") AlteracaoPrazoTarefaDTO tarefa) {

		LocalDateTime prazoMinimo = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).plusHours(1);

		model.addAttribute("tarefaId", id);
		model.addAttribute("prazoMinimo", prazoMinimo);

		return "tarefas/alterar-prazo";
	}

  	@PostMapping
	public String cadastrarTarefa(
		@ModelAttribute("tarefa") @Valid CadastroTarefaDTO tarefa,
		BindingResult result,
		RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return exibirFormularioCadastroTarefa(tarefa);
		}
		
		try {
			TarefaDTO tarefaCadastrada = tarefaApiService.cadastrarTarefa(tarefa);

			redirectAttributes.addFlashAttribute("tarefa", tarefaCadastrada);
			return "redirect:/tarefas/confirmacao-cadastro";
		} catch (BadRequest ex) {
			//TODO: Implementar captura de dados do HttpClientErrorException
			result.rejectValue("prazo", "tarefa", "Erro ao cadastrar tarefa");
			return exibirFormularioCadastroTarefa(tarefa);
		}
	}

	@PutMapping("{id}/marcar-visualizada")
	public String marcarVisualizada(@PathVariable UUID id) {
		
		tarefaApiService.marcarTarefaVisualizada(id);
		
		return "redirect:/tarefas";
	}
	
	@PutMapping("{id}/marcar-nao-visualizada")
	public String marcarNaoVisualizada(@PathVariable UUID id) {
		
		tarefaApiService.marcarTarefaNaoVisualizada(id);
		
		return "redirect:/tarefas";
	}

	@PostMapping("{id}/registrar-anotacao")
	public String registrarAnotacao(
		@PathVariable UUID id,
		@ModelAttribute("registroAnotacao") @Valid RegistrarAnotacaoTarefaDTO registroAnotacao) {
		
		tarefaApiService.registrarAnotacao(id, registroAnotacao);
		
		return "redirect:/tarefas";
	}

	@PutMapping("{id}/cancelar")
	public String cancelar(
		@PathVariable UUID id,
		@ModelAttribute("registroAnotacao") @Valid RegistrarAnotacaoTarefaDTO registroAnotacao) {
		
		tarefaApiService.cancelar(id, registroAnotacao);
		
		return "redirect:/tarefas";
	}

	@PutMapping("{id}/concluir")
	public String concluir(
		@PathVariable UUID id,
		@ModelAttribute("registroAnotacao") RegistrarAnotacaoTarefaDTO registroAnotacao) {
		
		tarefaApiService.concluir(id, registroAnotacao);
		
		return "redirect:/tarefas";
	}
}

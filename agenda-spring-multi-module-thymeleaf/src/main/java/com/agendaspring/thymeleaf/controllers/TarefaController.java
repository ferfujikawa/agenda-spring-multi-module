package com.agendaspring.thymeleaf.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agendaspring.thymeleaf.dtos.CadastroTarefaDTO;
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

  	@PostMapping
	public String cadastrarTarefa(
		@ModelAttribute("tarefa") @Valid CadastroTarefaDTO tarefa,
		BindingResult result,
		RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return exibirFormularioCadastroTarefa(tarefa);
		}
		
		ResponseEntity<?> resposta = tarefaApiService.cadastrarTarefa(tarefa);
		
		if (resposta.getStatusCode() == HttpStatus.BAD_REQUEST) {

			//TODO: Implementar captura de dados do HttpClientErrorException
			result.rejectValue("prazo", "tarefa", "Erro");
			return exibirFormularioCadastroTarefa(tarefa);
		}

		redirectAttributes.addFlashAttribute("tarefa", (TarefaDTO) resposta.getBody());
		return "redirect:/tarefas/confirmacao-cadastro";
	}
}

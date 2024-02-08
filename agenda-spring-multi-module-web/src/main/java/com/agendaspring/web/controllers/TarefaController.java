package com.agendaspring.web.controllers;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agendaspring.dominio.dtos.CadastroTarefaDTO;
import com.agendaspring.dominio.dtos.DataTableDTO;
import com.agendaspring.dominio.dtos.TarefaDTO;
import com.agendaspring.dominio.entities.Tarefa;
import com.agendaspring.dominio.services.ITarefaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("tarefas")
public class TarefaController {

    private ITarefaService tarefaService;

    public TarefaController(ITarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping(produces = "text/html")
	public String exibirPaginaListaTarefas() {
		
		return "tarefas/index";
	}
	
	@GetMapping(produces = "application/json")
	public @ResponseBody DataTableDTO<TarefaDTO> listarTarefas(
			@RequestParam("draw") int draw,
			@RequestParam("start") int inicioPagina,
            @RequestParam("length") int tamanhoPagina) {
		
		int numeroPagina = inicioPagina / tamanhoPagina;
		Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina, Sort.by(Sort.Direction.ASC, "prazo.valor"));
		
		return new DataTableDTO<TarefaDTO>(draw, tarefaService.listarTarefas(pageable).map(t -> new TarefaDTO(t)));
	}

	@GetMapping("nova")
	public String exibirFormularioCadastroTarefa(@ModelAttribute("tarefa") CadastroTarefaDTO tarefa) {
		
		return "tarefas/nova";
	}
	
	@PostMapping("nova")
	public String cadastrarTarefa(
		final Model model,
		@ModelAttribute("tarefa") @Valid CadastroTarefaDTO tarefa,
		BindingResult result,
		final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return exibirFormularioCadastroTarefa(tarefa);
		}
		
		Tarefa tarefaCadastrada = tarefaService.cadastrarTarefa(tarefa);

		redirectAttributes.addFlashAttribute("tarefa", new TarefaDTO(tarefaCadastrada));
		return "redirect:/tarefas/confirmacao-cadastro";
	}

	@GetMapping("confirmacao-cadastro")
	public String exibirConfirmacaoCadastro(@ModelAttribute("tarefa") TarefaDTO tarefa) {
		
		if (tarefa.getId() == null) {
			return "redirect:/tarefas";
		}

		return "tarefas/confirmacao-cadastro";
	}
}

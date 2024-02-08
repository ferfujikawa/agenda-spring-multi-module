package com.agendaspring.web.controllers;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
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
import com.agendaspring.dominio.dtos.HistoricoTarefaDTO;
import com.agendaspring.dominio.dtos.ListaPaginadaDTO;
import com.agendaspring.dominio.dtos.RegistrarAnotacaoTarefaDTO;
import com.agendaspring.dominio.dtos.TarefaDTO;
import com.agendaspring.dominio.entities.Tarefa;
import com.agendaspring.dominio.services.ITarefaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
		
		Pageable paginacao = PageRequest.of(pagina - 1, tamanhoPagina, Sort.by(Sort.Direction.DESC, "h.dataHistorico"));
		Page<HistoricoTarefaDTO> historicos = tarefaService.listarHistoricosDeTarefa(id, paginacao)
			.map(h -> new HistoricoTarefaDTO(h));
		
		ListaPaginadaDTO<HistoricoTarefaDTO> listaPaginada = new ListaPaginadaDTO<>(historicos);
		model.addAttribute("tarefaId", id);
		model.addAttribute("historicos", listaPaginada);
		
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

	@GetMapping("{id}/dar-andamento")
	public String exibirFormularioDarAndamento(
			final Model model,
			@PathVariable UUID id,
			@ModelAttribute("registroAnotacao") RegistrarAnotacaoTarefaDTO registroAnotacao) {
		
		model.addAttribute("tarefaId", id);
		
		return "tarefas/dar-andamento";
	}

	@GetMapping("{id}/cancelar")
	public String exibirFormularioCancelar(
			final Model model,
			@PathVariable UUID id,
			@ModelAttribute("registroAnotacao") RegistrarAnotacaoTarefaDTO registroAnotacao) {
		
		model.addAttribute("tarefaId", id);
		
		return "tarefas/cancelar";
	}

	@PostMapping("nova")
	public String cadastrarTarefa(
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

	@PostMapping("{id}/registrar-anotacao")
	public String registrarAnotacao(
			@PathVariable UUID id,
			@ModelAttribute("registroAnotacao") RegistrarAnotacaoTarefaDTO registroAnotacao) {
		
		tarefaService.registrarAnotacao(id, registroAnotacao);
		
		return "redirect:/tarefas";
	}

	@PutMapping("{id}/marcar-visualizada")
	public String marcarVisualizada(@PathVariable UUID id) {
		
		tarefaService.marcarTarefaVisualizada(id);
		
		return "redirect:/tarefas";
	}
	
	@PutMapping("{id}/marcar-nao-visualizada")
	public String marcarNaoVisualizada(@PathVariable UUID id) {
		
		tarefaService.marcarTarefaNaoVisualizada(id);
		
		return "redirect:/tarefas";
	}

	@PutMapping("{id}/dar-andamento")
	public String darAndamento(
			@PathVariable UUID id,
			@ModelAttribute("registroAnotacao") RegistrarAnotacaoTarefaDTO registroAnotacao) {
		
		tarefaService.darAndamento(id, registroAnotacao);
		
		return "redirect:/tarefas";
	}
}

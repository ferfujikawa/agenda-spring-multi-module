package com.agendaspring.web.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agendaspring.infra.services.ITarefaService;
import com.agendaspring.web.dtos.TarefaDTO;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("tarefas")
public class TarefaController {

    private ITarefaService tarefaService;

    public TarefaController(ITarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
	public String exibirPaginaListaTarefas(Model model, Pageable paginacao) {
		
        Page<TarefaDTO> tarefas = tarefaService.listarTarefas(paginacao)
            .map(t -> new TarefaDTO(t));

        model.addAttribute("tarefas", tarefas);

		return "tarefas/index";
	}
    
}

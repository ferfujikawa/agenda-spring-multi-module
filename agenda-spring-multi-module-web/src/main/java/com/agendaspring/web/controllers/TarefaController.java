package com.agendaspring.web.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agendaspring.infra.services.ITarefaService;
import com.agendaspring.web.dtos.DataTableDTO;
import com.agendaspring.web.dtos.TarefaDTO;

import org.springframework.web.bind.annotation.GetMapping;

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
}

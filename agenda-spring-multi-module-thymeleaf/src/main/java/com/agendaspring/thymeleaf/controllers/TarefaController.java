package com.agendaspring.thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tarefas")
public class TarefaController {

    @GetMapping
    public String exibirPaginaListaTarefas() {
		
		return "tarefas/index";
	}
}

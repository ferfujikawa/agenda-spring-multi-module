package com.agendaspring.thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tarefas")
public class TarefaController extends BaseController {

  @GetMapping
  public String exibirPaginaListaTarefas(Model model) {
  
    model.addAttribute("apiUrl", this.getApiUrl());

    return "tarefas/index";
  }
}

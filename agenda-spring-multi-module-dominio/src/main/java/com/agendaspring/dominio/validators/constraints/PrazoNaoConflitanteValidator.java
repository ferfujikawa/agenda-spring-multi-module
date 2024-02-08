package com.agendaspring.dominio.validators.constraints;

import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.agendaspring.dominio.services.ITarefaService;
import com.agendaspring.dominio.validators.PrazoNaoConflitante;

public class PrazoNaoConflitanteValidator implements ConstraintValidator<PrazoNaoConflitante, LocalDateTime> {

    private ITarefaService tarefaService;

    public PrazoNaoConflitanteValidator(ITarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        
        return !tarefaService.existeTarefaComMesmoPrazo(value);
    }
}

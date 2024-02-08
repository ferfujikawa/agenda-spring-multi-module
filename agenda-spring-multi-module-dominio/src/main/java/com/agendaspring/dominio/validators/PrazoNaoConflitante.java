package com.agendaspring.dominio.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.agendaspring.dominio.validators.constraints.PrazoNaoConflitanteValidator;

@Constraint(validatedBy = PrazoNaoConflitanteValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
@Documented
public @interface PrazoNaoConflitante {

    String message() default "O prazo está em conflito com outra tarefa";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

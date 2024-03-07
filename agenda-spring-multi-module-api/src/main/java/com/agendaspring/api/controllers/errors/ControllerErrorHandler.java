package com.agendaspring.api.controllers.errors;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroValidacao>> handleBadRequest(MethodArgumentNotValidException ex) {
        
        List<ErroValidacao> erros = ex.getFieldErrors().stream()
            .map(e -> new ErroValidacao(e))
            .collect(Collectors.toList());
        
        return ResponseEntity.badRequest().body(erros);
    }
}

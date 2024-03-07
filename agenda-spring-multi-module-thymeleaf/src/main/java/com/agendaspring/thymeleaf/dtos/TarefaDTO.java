package com.agendaspring.thymeleaf.dtos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class TarefaDTO {

    private UUID id;
	private String titulo;
	private LocalDateTime dataCadastro;
	private Boolean visualizada;
	private String situacao;
	private LocalDateTime prazo;
	private Long horasParaPrazo;
    private Boolean podeMarcarComoVisualizada;
    private Boolean podeMarcarComoNaoVisualizada;
    private Boolean podeDarAndamento;
    private Boolean podeCancelar;
    private Boolean podeConcluir;
    private Boolean podeAlterarPrazo;

    protected TarefaDTO() {}
	
	public TarefaDTO(UUID id, String titulo, LocalDateTime dataCadastro, Boolean visualizada, String situacao,
			LocalDateTime prazo, Boolean podeMarcarComoVisualizada, Boolean podeMarcarComoNaoVisualizada,
            Boolean podeDarAndamento, Boolean podeCancelar, Boolean podeConcluir, Boolean podeAlterarPrazo) {
		this.id = id;
		this.titulo = titulo;
		this.dataCadastro = dataCadastro;
		this.visualizada = visualizada;
		this.situacao = situacao;
		this.prazo = prazo;
        this.podeMarcarComoVisualizada = podeMarcarComoVisualizada;
        this.podeMarcarComoNaoVisualizada = podeMarcarComoNaoVisualizada;
        this.podeDarAndamento = podeDarAndamento;
        this.podeCancelar = podeCancelar;
        this.podeConcluir = podeConcluir;
        this.podeAlterarPrazo = podeAlterarPrazo;
		
		LocalDateTime horaAtual = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
		this.horasParaPrazo = horaAtual.until(this.prazo, ChronoUnit.HOURS); 
	}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getVisualizada() {
        return visualizada;
    }

    public void setVisualizada(Boolean visualizada) {
        this.visualizada = visualizada;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public LocalDateTime getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDateTime prazo) {
        this.prazo = prazo;
    }

    public Long getHorasParaPrazo() {
        return horasParaPrazo;
    }

    public void setHorasParaPrazo(Long horasParaPrazo) {
        this.horasParaPrazo = horasParaPrazo;
    }

    public Boolean getPodeMarcarComoVisualizada() {
        return podeMarcarComoVisualizada;
    }

    public void setPodeMarcarComoVisualizada(Boolean podeMarcarComoVisualizada) {
        this.podeMarcarComoVisualizada = podeMarcarComoVisualizada;
    }

    public Boolean getPodeMarcarComoNaoVisualizada() {
        return podeMarcarComoNaoVisualizada;
    }

    public void setPodeMarcarComoNaoVisualizada(Boolean podeMarcarComoNaoVisualizada) {
        this.podeMarcarComoNaoVisualizada = podeMarcarComoNaoVisualizada;
    }

    public Boolean getPodeDarAndamento() {
        return podeDarAndamento;
    }

    public void setPodeDarAndamento(Boolean podeDarAndamento) {
        this.podeDarAndamento = podeDarAndamento;
    }

    public Boolean getPodeCancelar() {
        return podeCancelar;
    }

    public void setPodeCancelar(Boolean podeCancelar) {
        this.podeCancelar = podeCancelar;
    }

    public Boolean getPodeConcluir() {
        return podeConcluir;
    }

    public void setPodeConcluir(Boolean podeConcluir) {
        this.podeConcluir = podeConcluir;
    }

    public Boolean getPodeAlterarPrazo() {
        return podeAlterarPrazo;
    }

    public void setPodeAlterarPrazo(Boolean podeAlterarPrazo) {
        this.podeAlterarPrazo = podeAlterarPrazo;
    }
}


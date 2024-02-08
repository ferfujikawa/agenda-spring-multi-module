package com.agendaspring.dominio.dtos;

import java.util.List;

import org.springframework.data.domain.Page;

public class ListaPaginadaDTO<T> {

	private Integer pagina;
	
	private Integer tamanhoPagina;
    
	private Integer totalPaginas;
	
    private Long totalRegistros;
    
    private Integer totalPaginaAtual;
    
    private List<T> registros;
	
	public ListaPaginadaDTO(Page<T> page) {
		
		this.pagina = page.getNumber() + 1;
		this.tamanhoPagina = page.getSize();
		this.totalPaginas = page.getTotalPages();
		this.totalRegistros = page.getTotalElements();
		this.totalPaginaAtual = page.getContent().size();
		this.registros = page.getContent();
	}

	public Integer getPagina() {
		return pagina;
	}

	public Integer getTamanhoPagina() {
		return tamanhoPagina;
	}

	public Integer getTotalPaginas() {
		return totalPaginas;
	}

	public Long getTotalRegistros() {
		return totalRegistros;
	}

	public Integer getTotalPaginaAtual() {
		return totalPaginaAtual;
	}

	public List<T> getRegistros() {
		return registros;
	}
}

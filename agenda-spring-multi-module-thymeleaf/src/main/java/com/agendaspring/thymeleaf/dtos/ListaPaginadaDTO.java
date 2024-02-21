package com.agendaspring.thymeleaf.dtos;

import java.util.List;

public class ListaPaginadaDTO<T> {

	private Integer pagina;
	
	private Integer tamanhoPagina;
    
	private Integer totalPaginas;
	
    private Long totalRegistros;
    
    private Integer totalPaginaAtual;
    
    private List<T> registros;
	
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

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

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	public void setTamanhoPagina(Integer tamanhoPagina) {
		this.tamanhoPagina = tamanhoPagina;
	}

	public void setTotalPaginas(Integer totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public void setTotalPaginaAtual(Integer totalPaginaAtual) {
		this.totalPaginaAtual = totalPaginaAtual;
	}

	public void setRegistros(List<T> registros) {
		this.registros = registros;
	}
}

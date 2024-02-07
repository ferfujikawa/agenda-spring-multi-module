package com.agendaspring.web.dtos;

import java.util.List;

import org.springframework.data.domain.Page;

public class DataTableDTO<T> {

	private int draw;
    
    private long recordsTotal;
    
    private long recordsFiltered;
    
    private List<T> data;
	
	public DataTableDTO(int draw, Page<T> page) {
		this.draw = draw;
		this.recordsTotal = page.getTotalElements();
		this.recordsFiltered = page.getTotalElements();
		this.data = page.getContent();
	}

	public int getDraw() {
		return draw;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}    
}


package com.agendaspring.thymeleaf.dtos;

import java.util.List;

public class DataTableDTO<T> {

	private int draw;
    
    private long recordsTotal;
    
    private long recordsFiltered;
    
    private List<T> data;

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

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}

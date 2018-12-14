package com.ksign.prod.dto;

import java.util.List;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DataTableVO {
	
	//in
	private int draw; 
	private int start;
	private int length;
	
	private String orderColumn;
	private String orderDir;//asc,desc
	
	//out
	private long recordsFiltered;
	private long recordsTotal;
	private List<?> data;
	
	public int getPageNo() {
		//return (start > 0 ? start / length : start) + 1; // oracle
		return start;  // mysql
	}
	
	public void setRecordsTotal(long recordsTotal){
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsTotal;
	}

	public DataTableVO() {	}
}
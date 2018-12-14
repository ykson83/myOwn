package com.ksign.prod.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -5691384892142230918L;
	protected static final String MY_TIME_ZONE="Asia/Seoul";
	
	protected String description;
	
	protected int status;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone=MY_TIME_ZONE)
	protected Date addDate;
	protected String addUser;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone=MY_TIME_ZONE)
	protected Date modDate;
	protected String modUser;
}

package com.ksign.prod.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseObjectDTO implements Serializable {
	private static final long serialVersionUID = -6572357044138295971L;
	
	int code;
	String message;
	
	Object object;
}

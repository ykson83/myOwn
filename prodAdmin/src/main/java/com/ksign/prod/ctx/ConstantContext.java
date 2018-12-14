package com.ksign.prod.ctx;

import com.ksign.prod.dto.ResponseObjectDTO;

public class ConstantContext {

	//product-type
	public static final int PD_TYPE_FILE = 1;
	public static final int PD_TYPE_CONF = 2;
	public static final int[] PD_TYPE = { PD_TYPE_FILE, PD_TYPE_CONF };

	public static String toPDTypeStr(int type) {
		switch(type) {
		case PD_TYPE_FILE: return "FILE";
		case PD_TYPE_CONF: return "CONFIG";
		default: return "Unknown";
		}
	}
	
	//status
	public static final int STATUS_ON = 1; 
	public static final int STATUS_OFF = 2; 
	public static final int[] STATUS = { STATUS_ON, STATUS_OFF };
	
	public static String toStatusStr(int status) {
		switch(status) {
		case STATUS_ON: return "사용";
		case STATUS_OFF: return "정지";
		default: return "선택";
		}
	}
	
	// resp code
	public class RespCode {
		
		public static final int OK = 200;
		
		public static final int BAD_REQUEST = 400;
		public static final int NOT_FOUND = 404;
		
		public static final int DOES_NOT_EXIST = 561; // Entity가 존재 하지 않을 경우 오류 코드
		public static final int ALREADY_EXIST = 562;  // Entity가 존재할경우 오류 코드
	}
	
	public static ResponseObjectDTO responseDto(int code) {
		ResponseObjectDTO obj = new ResponseObjectDTO();
		obj.setCode(code);
		
		switch(code) {
		case RespCode.OK: 
			obj.setMessage("OK");
			break;
		case RespCode.BAD_REQUEST: 
			obj.setMessage("BadRequest");
			break;
		case RespCode.NOT_FOUND: 
			obj.setMessage("NotFound");
			break;
		
		default: 
			obj.setMessage("뭥미");
			break;
		}
		
		return obj;
	}
}

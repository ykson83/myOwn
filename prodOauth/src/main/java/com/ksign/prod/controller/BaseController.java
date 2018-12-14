package com.ksign.prod.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ksign.prod.ctx.ConstantContext;
import com.ksign.prod.dto.ResponseObjectDTO;
import com.ksign.prod.entity.BaseEntity;

public class BaseController {
	protected ObjectMapper mapper = new ObjectMapper();
	
	@ExceptionHandler
	public ResponseObjectDTO errorHandle(Exception e, HttpServletResponse response) {
		ResponseObjectDTO respDto = new ResponseObjectDTO();
		respDto.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		respDto.setMessage(e.getMessage());
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		e.printStackTrace();
		
		return respDto;
	}
	
	public void setInitData(BaseEntity entity, boolean isUpdate) {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		
		entity.setModUser(auth.getName());
		entity.setModDate(new Date(System.currentTimeMillis()));

		if(isUpdate) return;
		
		entity.setAddUser(auth.getName());
		entity.setAddDate(new Date(System.currentTimeMillis()));
	}
	
	public ResponseObjectDTO responseObjectDTO(int code, String msg, Object obj) {
		ResponseObjectDTO result = new ResponseObjectDTO();
		result.setCode(code);
		result.setMessage(msg);
		result.setObject(obj);
		
		return result;
	}
	
	public ResponseObjectDTO responseObjectDTO(Object obj) {
		return responseObjectDTO(ConstantContext.RespCode.OK, null, obj);
	}
}
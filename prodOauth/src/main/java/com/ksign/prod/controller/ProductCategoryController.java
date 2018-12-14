package com.ksign.prod.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksign.prod.ctx.ConstantContext;
import com.ksign.prod.ctx.ConstantContext.RespCode;
import com.ksign.prod.dto.DataTableVO;
import com.ksign.prod.dto.ResponseObjectDTO;
import com.ksign.prod.entity.ProductCategoryEntity;
import com.ksign.prod.service.ProductCategoryService;
import com.ksign.prod.util.SequenceUtil;
import com.ksign.prod.util.WebUtil;

@RestController
@RequestMapping("/api/productCategory")
public class ProductCategoryController extends BaseController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired ProductCategoryService pCateService;
	@Autowired WebUtil webUtil;
	@Autowired SequenceUtil seqUtil;
	
	@GetMapping(value = "list_with_count")
	public Object listWithCount(HttpServletRequest req, HttpServletResponse resp, DataTableVO reqCond) throws IOException {
		log.info("get list_with_count/status : " + req.getParameter("status"));
		
		HashMap<String, Object> param = new HashMap<String, Object>();
	
		param.put("name", req.getParameter("name"));
		param.put("description", req.getParameter("description"));
		
		int status = 2;
		if(req.getParameter("status") != null) {
			try { status = Integer.parseInt(req.getParameter("status").trim()); } catch(Exception ignored) {}
		}
		
		param.put("status", status);
		
		webUtil.setPagi(req, reqCond, param);
		webUtil.setDateRange(req, param);
		
		DataTableVO dataVO = pCateService.listWithCount(param);
		if(log.isDebugEnabled()) log.debug("list_with_count: " + mapper.writeValueAsString(dataVO));
		
		return dataVO;
	}
	
	@GetMapping(value = "{objectId}")
	public Object getObject(
			HttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String objectId
			) throws IOException 
	{	
		ResponseObjectDTO respDto = new ResponseObjectDTO();
		ProductCategoryEntity _p = (ProductCategoryEntity) pCateService.select(objectId);
		
		if(_p != null) {
			respDto.setCode(200);
			respDto.setObject(_p);
		} 
		return _p != null ? 
				responseObjectDTO(ConstantContext.RespCode.OK, null, _p) :
					responseObjectDTO(ConstantContext.RespCode.NOT_FOUND, "cannot find object: " + objectId, null);
	
	}

	//카테고리 name 중복체크
	@GetMapping(value = "/nameCheck/{objectId}") 
	public Object checkObject(
			HttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String objectId
			) throws IOException 
	{	
		ProductCategoryEntity _p = (ProductCategoryEntity) pCateService.selectByName(objectId);
		
		return _p != null ? 
				responseObjectDTO(ConstantContext.RespCode.OK, "이미 존재하는 name입니다", null) :
					responseObjectDTO(ConstantContext.RespCode.NOT_FOUND, "사용가능한 name입니다: " + objectId, null);
	}
	
	@PostMapping(value = "/")
	public Object addObject(
			HttpServletRequest req,
			HttpServletResponse resp,
			@RequestBody String inputObj
			) throws IOException 
	{
		ProductCategoryEntity _t = mapper.readValue(inputObj, ProductCategoryEntity.class);
		
		setInitData(_t, false);
		pCateService.insert(_t);
		
		return  responseObjectDTO(ConstantContext.RespCode.OK, "insert success: " + _t.getName(), null);
		
	}
	
	@PatchMapping(value = "{objectId}")
	public Object updateObject(
			HttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String objectId,
			@RequestBody String inputObj
			) throws IOException
	{
		ProductCategoryEntity _t = mapper.readValue(inputObj, ProductCategoryEntity.class);

		if(_t != null) {
			setInitData(_t, true);//수정 후 addDate 그대로인지 확인
			pCateService.update(_t);
		}
		
		return _t != null ?
				responseObjectDTO(RespCode.OK, "update success: " + _t.getName(), null) : 
					responseObjectDTO(RespCode.NOT_FOUND, "cannot find object", null);
	
	}
	
	@DeleteMapping(value = "{objectId}")
	public Object deleteObject(
			HttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String objectId
			) throws IOException 
	{
		ProductCategoryEntity _p = (ProductCategoryEntity) pCateService.select(objectId);
		
		if(_p != null) {
			_p.setStatus(ConstantContext.STATUS_OFF);
			setInitData(_p, true);
			pCateService.delete(_p);
		}
		return _p != null ?
				responseObjectDTO(RespCode.OK, "deleted: " + objectId, null) : 
					responseObjectDTO(RespCode.NOT_FOUND, "cannot find object: " + objectId, null);
	}

}
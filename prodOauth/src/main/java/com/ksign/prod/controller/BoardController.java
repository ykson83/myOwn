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
import com.ksign.prod.entity.BoardEntity;
import com.ksign.prod.service.BoardCategoryService;
import com.ksign.prod.service.BoardService;
import com.ksign.prod.util.SequenceUtil;
import com.ksign.prod.util.WebUtil;

@RestController
@RequestMapping("/api/board")
public class BoardController extends BaseController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired BoardService boardServ;
	@Autowired BoardCategoryService bCateServ;  
	@Autowired WebUtil webUtil;
	@Autowired SequenceUtil seqUtil;
	
	@GetMapping(value = "list_with_count")
	public Object listWithCount(HttpServletRequest req, HttpServletResponse resp, DataTableVO reqCond) throws IOException {
		log.info("get list_with_count");
		
		HashMap<String, Object> param = new HashMap<String, Object>();
	
		param.put("title", req.getParameter("title"));
		param.put("boardCategoryId", req.getParameter("boardCategoryId"));
		
		int status = webUtil.parseInt(req.getParameter("status"), 2);
		param.put("status", status);
		param.put("addUser", req.getParameter("addUser"));
		
		webUtil.setPagi(req, reqCond, param);
		webUtil.setDateRange(req, param);
		
		DataTableVO dataVO = boardServ.listWithCount(param);
		
		return dataVO;
	}
	
	@GetMapping(value = "{objectId}")
	public Object getObject(
			HttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String objectId
			) throws IOException 
	{	
		BoardEntity _p = (BoardEntity) boardServ.select(objectId);
		
		return _p != null ? 
				responseObjectDTO(ConstantContext.RespCode.OK, null, _p) :
					responseObjectDTO(ConstantContext.RespCode.NOT_FOUND, "cannot find object: " + objectId, _p);
	}
	
	@PostMapping(value = "/")
	public Object addObject(
			HttpServletRequest req,
			HttpServletResponse resp,
			@RequestBody String inputObj
			) throws IOException 
	{
		BoardEntity _t = mapper.readValue(inputObj, BoardEntity.class);
		
		setInitData(_t, false);
		boardServ.insert(_t);
		return responseObjectDTO(ConstantContext.RespCode.OK, "insert success", null);
	}
		
	
	@PatchMapping(value = "/update/{objectId}")
	public Object updateObject(
			HttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String objectId,
			@RequestBody String inputObj
			) throws IOException
	{
		BoardEntity _t = mapper.readValue(inputObj, BoardEntity.class);
		BoardEntity _p = (BoardEntity) boardServ.select(_t.getBoardId());

		if(_p != null) {
			setInitData(_t, true);
			boardServ.update(_t);
		}
		return _p != null ?
				responseObjectDTO(RespCode.OK, "update success: " + objectId, null) : 
					responseObjectDTO(RespCode.NOT_FOUND, "cannot find object: " + objectId, null);
	}
	
	@DeleteMapping(value = "{objectId}")
	public Object deleteObject(
			HttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String objectId
			) throws IOException 
	{
		BoardEntity _p = (BoardEntity) boardServ.select(objectId);
		
		if(_p != null) {
			_p.setStatus(ConstantContext.STATUS_OFF);
			setInitData(_p, true);
			boardServ.delete(_p);
		}
		return _p != null ?
				responseObjectDTO(RespCode.OK, "deleted: " + objectId, null) : 
					responseObjectDTO(RespCode.NOT_FOUND, "cannot find object: " + objectId, null);
	}
}
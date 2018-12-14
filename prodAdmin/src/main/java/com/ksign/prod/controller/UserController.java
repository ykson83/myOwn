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

import com.ksign.prod.auth.UserPasswordEncoder;
import com.ksign.prod.ctx.ConstantContext;
import com.ksign.prod.ctx.ConstantContext.RespCode;
import com.ksign.prod.dto.DataTableVO;
import com.ksign.prod.entity.UserEntity;
import com.ksign.prod.service.UserService;
import com.ksign.prod.util.WebUtil;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired UserService userServ;
	@Autowired WebUtil webUtil;
	@Autowired UserPasswordEncoder passwordEncoder;
	
	@GetMapping(value = "list_with_count")
	public Object listWithCount(HttpServletRequest req, HttpServletResponse resp, DataTableVO reqCond) throws IOException {
		log.info("get list_with_count");
		
		HashMap<String, Object> param = new HashMap<String, Object>();
	
		param.put("userId", req.getParameter("userId"));
		param.put("name", req.getParameter("name"));
		param.put("dept", req.getParameter("dept"));
		
		int type = webUtil.parseInt(req.getParameter("type"), 0);
		param.put("type", type);
		
		webUtil.setPagi(req, reqCond, param);
		webUtil.setDateRange(req, param);
		
		DataTableVO dataVO = userServ.listWithCount(param);
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
		UserEntity _p = (UserEntity) userServ.select(objectId);
		
		return _p != null ? 
				responseObjectDTO(ConstantContext.RespCode.OK, null, _p) :
					responseObjectDTO(ConstantContext.RespCode.NOT_FOUND, "cannot find object: " + objectId, null);
	}
	
	@PostMapping(value = "{objectId}")
	public Object addObject(
			HttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String objectId,
			@RequestBody String inputObj
			) throws IOException 
	{
		UserEntity _t = mapper.readValue(inputObj, UserEntity.class);
		UserEntity _p = (UserEntity) userServ.select(objectId);

		if(_p == null) {
			//password 인코딩
			String password = passwordEncoder.encode(_t.getPassword());
			_t.setPassword(password);
			
			setInitData(_t, false);
			userServ.insert(_t);
		}
		 
		return _p == null ? 
				responseObjectDTO(ConstantContext.RespCode.OK, "insert success: " + _t.getUserId(), null) :
					responseObjectDTO(ConstantContext.RespCode.NOT_FOUND, "already exsist id: " + _t.getUserId(), null);
	}

	@PatchMapping(value = "{objectId}")
	public Object updateObject(
			HttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String objectId,
			@RequestBody String inputObj
			) throws IOException
	{
		UserEntity user = mapper.readValue(inputObj, UserEntity.class);
		
		setInitData(user, true);
		userServ.update(user);
		
		UserEntity _p = (UserEntity) userServ.select(user.getUserId());
		
		return _p != null ?
				responseObjectDTO(ConstantContext.RespCode.OK, "update success: " + _p.getUserId(), _p) :
					responseObjectDTO(ConstantContext.RespCode.NOT_FOUND, "cannot find object, " + objectId, null);
	}
	
	@DeleteMapping(value = "{objectId}")
	public Object deleteObject(
			HttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String objectId
			) throws IOException 
	{
		UserEntity _p = (UserEntity) userServ.select(objectId);
		
		if(_p != null) {
			_p.setStatus(ConstantContext.STATUS_OFF);
			setInitData(_p, true);
			userServ.delete(_p);
		}
		
		return _p != null ?
				responseObjectDTO(RespCode.OK, "deleted: " + objectId, null) : 
					responseObjectDTO(RespCode.NOT_FOUND, "cannot find object: " + objectId, null);

	}
}

package com.ksign.prod.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ksign.prod.ctx.ConstantContext;
import com.ksign.prod.ctx.ConstantContext.RespCode;
import com.ksign.prod.dto.DataTableVO;
import com.ksign.prod.entity.DownloadHistoryEntity;
import com.ksign.prod.entity.ProductCategoryEntity;
import com.ksign.prod.entity.ProductEntity;
import com.ksign.prod.service.DownloadHistoryService;
import com.ksign.prod.service.ProductCategoryService;
import com.ksign.prod.service.ProductService;
import com.ksign.prod.util.SequenceUtil;
import com.ksign.prod.util.WebUtil;

@RestController
@RequestMapping("/api/product")
public class ProductController extends BaseController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired ProductService productService;
	@Autowired ProductCategoryService pCateService;
	@Autowired DownloadHistoryService historyService;
	@Autowired WebUtil webUtil;
	@Autowired SequenceUtil seqUtil;
	
	@GetMapping(value = "list_with_count")
	public Object listWithCount(HttpServletRequest req, HttpServletResponse resp, DataTableVO reqCond) throws IOException {
		log.info("get list_with_count," + mapper.writeValueAsString(reqCond));
		
		HashMap<String, Object> param = new HashMap<String, Object>();
	
		param.put("name", req.getParameter("name"));
//		param.put("type", req.getParameter("type"));
		param.put("version", req.getParameter("version"));
		param.put("fileName", req.getParameter("fileName"));
		if("0".equals(req.getParameter("productCategoryId"))) {
			param.put("productCategoryId", "");
		}
		param.put("productCategoryId", req.getParameter("productCategoryId"));
		
		int status = webUtil.parseInt(req.getParameter("status"), 2);
		param.put("status", status);
		
		webUtil.setPagi(req, reqCond, param);
		webUtil.setDateRange(req, param);
		
		DataTableVO dataVO = productService.listWithCount(param);

		return dataVO;
	}
	
	
	@GetMapping(value = "historyList_with_count")
	public Object productListWithCount(HttpServletRequest req, HttpServletResponse resp, DataTableVO reqCond) throws IOException {
		log.info("get list_with_count");
		
		HashMap<String, Object> param = new HashMap<String, Object>();
	
		param.put("historyId", req.getParameter("historyId"));
		param.put("name", req.getParameter("name"));//product의 name
		param.put("purpose", req.getParameter("purpose"));
		param.put("addDate", req.getParameter("addDate"));
		
		webUtil.setPagi(req, reqCond, param);
		webUtil.setDateRange(req, param);
		
		DataTableVO dataVO = historyService.listWithCount(param);

		return dataVO;
	}
	
	
	@GetMapping(value = "{objectId}")
	public Object getObject(
			HttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String objectId
			
			) throws IOException 
	{		
		ProductEntity _p = (ProductEntity) productService.select(objectId);
		
		return _p != null ? 
				responseObjectDTO(ConstantContext.RespCode.OK, null, _p) :
					responseObjectDTO(ConstantContext.RespCode.NOT_FOUND, "cannot find object: " + objectId, null);
	}
	
	
	@GetMapping(value = "historyDetail/{objectId}")
	public Object getHistoryObject(
			HttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String objectId
			
			) throws IOException 
	{		
		DownloadHistoryEntity _p = (DownloadHistoryEntity) historyService.select(objectId);
		
		return _p != null ? 
				responseObjectDTO(ConstantContext.RespCode.OK, null, _p) :
					responseObjectDTO(ConstantContext.RespCode.NOT_FOUND, "cannot find object: " + objectId, null);
	}
	
	
	@PostMapping(value = "downloadHistory/{objectId}")
	public Object addHistory(
			HttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String objectId,
			@RequestBody String inputObj
			) throws IOException 
	{
		//downloadHistory에 입력
		DownloadHistoryEntity _t = mapper.readValue(inputObj, DownloadHistoryEntity.class);
		
		setInitData(_t, false);
		_t.setStatus(ConstantContext.STATUS_ON);
		String ticket = historyService.insert(_t);
		
		if(StringUtils.isEmpty(ticket)) {
			throw new NullPointerException();
		} else {
			HttpSession session = req.getSession();
			session.setAttribute(ticket, _t);
			
			HashMap<String, String> map = new HashMap<>();
			map.put("ticket", ticket);

			return responseObjectDTO(ConstantContext.RespCode.OK, "generate ticket", map);
		}
	}
	
	@GetMapping(value = "download/{ticket}")
	public void download(
			HttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String ticket
			) throws IOException 
	{	
		HttpSession session = req.getSession();
		
		if(StringUtils.isEmpty(ticket)) {
			throw new NullPointerException("download fail: no ticket");
		} else {
			DownloadHistoryEntity _pp = (DownloadHistoryEntity) session.getAttribute(ticket);
			try { session.removeAttribute(ticket); } catch(Exception ignored) { }
			
			ProductEntity _p = (ProductEntity) productService.select(_pp.getProduct().getProductId());
			
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + _p.getFileName() + "\";");
			resp.setHeader("Content-Transfer-Encoding", "binary");
			resp.getOutputStream().write(_p.getFile());
		}
	}
	
	@PostMapping(value = "/")
	public Object addObject(
			MultipartHttpServletRequest req,
			HttpServletResponse resp,
			ProductEntity product
			
			) throws IOException 
	{
		MultipartFile file = req.getFile("fileObj");
		
		String productId = req.getParameter("productCategoryId");
		ProductCategoryEntity pc = (ProductCategoryEntity) pCateService.select(productId);
		product.setProductCategory(pc);
		
		if(file != null) {
			byte[] fileBytes = file.getBytes();
			product.setFile(fileBytes);
			product.setFileName(file.getOriginalFilename());
		}
		setInitData(product, false);
		productService.insert(product);
		
		return  responseObjectDTO(ConstantContext.RespCode.OK, "insert success: " + product.getName(), null);
	
	}
		
	@PostMapping(value = "{objectId}")
	public Object updateObject(
			MultipartHttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String objectId,
			ProductEntity product
			) throws IOException
	{
		String pdCateId = req.getParameter("productCategoryId");
		
		ProductCategoryEntity pc = (ProductCategoryEntity) pCateService.select(pdCateId);
		product.setProductCategory(pc);
		
		MultipartFile file = req.getFile("fileObj");
		
		if(file != null) {
			byte[] fileBytes = file.getBytes();
			product.setFile(fileBytes);
			product.setFileName(file.getOriginalFilename());
		}
		ProductEntity _p = (ProductEntity) productService.select(product.getProductId());
		
		if(_p != null) {
			setInitData(product, true);
			productService.update(product);
		}
		
		return _p != null ?
				responseObjectDTO(ConstantContext.RespCode.OK, "update success: " + product.getProductId(), null) :
					responseObjectDTO(ConstantContext.RespCode.NOT_FOUND, "cannot find object: " + product.getProductId(), null);
	}

	@DeleteMapping(value = "{objectId}")
	public Object statusUpdate(
			HttpServletRequest req,
			HttpServletResponse resp,
			@PathVariable String objectId
			) throws IOException 
	{
		ProductEntity _p = (ProductEntity) productService.select(objectId);
		
		if(_p != null) {
			_p.setStatus(ConstantContext.STATUS_OFF);
			setInitData(_p, true);
			productService.delete(_p);
		}
		
		return _p != null ?
				responseObjectDTO(RespCode.OK, "deleted: " + objectId, null) : 
					responseObjectDTO(RespCode.NOT_FOUND, "cannot find object: " + objectId, null);
	
	}
}
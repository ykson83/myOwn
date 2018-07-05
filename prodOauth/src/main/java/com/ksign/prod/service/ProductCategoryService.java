package com.ksign.prod.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksign.prod.dto.DataTableVO;
import com.ksign.prod.entity.ProductCategoryEntity;
import com.ksign.prod.mapper.impl.ProductCategoryRepository;
import com.ksign.prod.util.SequenceUtil;

@Service("productCategoryServ")
public class ProductCategoryService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired ProductCategoryRepository pCateRepo;
	@Autowired SequenceUtil seqUtil;
	
	public DataTableVO listWithCount(HashMap<String, Object> param) {
		if(log.isDebugEnabled()) log.debug("get list: " + param.toString());
		DataTableVO table = new DataTableVO();
		
		table.setRecordsFiltered(pCateRepo.selectListCount(param));
		table.setData(pCateRepo.selectList(param));
		
		return table;
	}
	
	public Object select(String objectId) {
		return pCateRepo.select(objectId);
	}
	
	public Object selectByName(String name) {
		ProductCategoryEntity _p = pCateRepo.selectByName(name);
		
		if(_p != null) {
			return _p;
		} else {
			return null;
		}
	}

	@Transactional
	public void insert(ProductCategoryEntity pCate) {
		String productCategoryId = seqUtil.getNextId("productCategory", "PC", 12);
		pCate.setProductCategoryId(productCategoryId);
		ProductCategoryEntity _p = pCateRepo.select(productCategoryId);
		
		if(_p == null) {
			pCateRepo.insert(pCate);
		} else {
			throw new IllegalStateException("already exists");
		}
	}

	@Transactional
	public void delete(ProductCategoryEntity pCate) {//존재하면? delete
		ProductCategoryEntity _p = pCateRepo.select(pCate.getProductCategoryId());
		if(_p != null) {
			pCateRepo.delete(pCate);
		} else {
			throw new IllegalStateException("cannot find object");
		}
	}

	@Transactional
	public void update(ProductCategoryEntity pCate) {//존재하면? update
		ProductCategoryEntity pCatetForCheck = pCateRepo.select(pCate.getProductCategoryId());
		if(pCatetForCheck != null) {
			pCateRepo.update(pCate);
		} else {
			throw new IllegalStateException("cannot find object");
		}
	}
}

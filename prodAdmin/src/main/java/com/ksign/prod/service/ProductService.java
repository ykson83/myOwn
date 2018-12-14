package com.ksign.prod.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ksign.prod.dto.DataTableVO;
import com.ksign.prod.entity.ProductEntity;
import com.ksign.prod.mapper.impl.ProductRepository;
import com.ksign.prod.util.SequenceUtil;

@Service("productServ")
public class ProductService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired ProductRepository productRepo;
	@Autowired SequenceUtil seqUtil;
	
	public DataTableVO listWithCount(HashMap<String, Object> param) {
		if(log.isDebugEnabled()) log.debug("get list: " + param.toString());
		DataTableVO table = new DataTableVO();
		
		table.setRecordsFiltered(productRepo.selectListCount(param));
		table.setData(productRepo.selectList(param));
		
		return table;
	}
	
	public Object select(String objectId) {
		return productRepo.select(objectId);
	}

	@Transactional
	public void insert(ProductEntity product) {
		String pdId = seqUtil.getNextId("product", "P", 12);
		product.setProductId(pdId);
		
		ProductEntity _p = productRepo.select(product.getProductId());

		if(_p == null) {//중복 없으면 insert
			productRepo.insert(product);
		} else {
			throw new IllegalStateException("connot find object");
		}
	}

	@Transactional
	public void update(ProductEntity product) {//존재하면? update
		ProductEntity _p = productRepo.select(product.getProductId());
		
		if(_p != null) {
			if(StringUtils.isEmpty(product.getFileName())) {
				product.setFile(_p.getFile());
				product.setFileName(_p.getFileName());
			}
			productRepo.update(product);
		} else {
			throw new IllegalStateException("connot find object");
		}
	}
	
	@Transactional
	public void delete(ProductEntity product) {//존재하면? update
		ProductEntity _p = productRepo.select(product.getProductId());
		
		if(_p != null) {
			productRepo.delete(product);
		} else {
			throw new IllegalStateException("connot find object");
		}
	}
}
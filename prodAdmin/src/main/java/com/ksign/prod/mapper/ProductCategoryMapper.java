package com.ksign.prod.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.ksign.prod.entity.ProductCategoryEntity;

public interface ProductCategoryMapper {
	public ArrayList<ProductCategoryEntity> selectList(HashMap<String, Object> paramMap);
	public int selectListCount(HashMap<String, Object> paramMap);
	public ProductCategoryEntity select(String productCategoryId);
	public ProductCategoryEntity selectByName(String name);
	public void update(ProductCategoryEntity productCategory);
	public void insert(ProductCategoryEntity productCategory);
	public void delete(ProductCategoryEntity productCategory);
}

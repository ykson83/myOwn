package com.ksign.prod.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.ksign.prod.entity.ProductEntity;

public interface ProductMapper {
	public ArrayList<ProductEntity> selectList(HashMap<String, Object> paramMap);
	public int selectListCount(HashMap<String, Object> paramMap);
	public ProductEntity select(String productId);
	public void update(ProductEntity product);
	public void insert(ProductEntity product);
	public void delete(ProductEntity product);
}

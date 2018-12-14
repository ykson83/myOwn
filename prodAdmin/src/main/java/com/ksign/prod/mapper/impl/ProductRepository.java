package com.ksign.prod.mapper.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksign.prod.entity.ProductEntity;
import com.ksign.prod.mapper.ProductMapper;

@Repository("prodRepo")
public class ProductRepository implements ProductMapper {
	@Autowired SqlSessionTemplate sqlSession;

	@Override
	public ArrayList<ProductEntity> selectList(HashMap<String, Object> paramMap) {
		return sqlSession.getMapper(ProductMapper.class).selectList(paramMap);
	}

	@Override
	public int selectListCount(HashMap<String, Object> paramMap) {
		return sqlSession.getMapper(ProductMapper.class).selectListCount(paramMap);
	}

	@Override
	public ProductEntity select(String productId) {
		return sqlSession.getMapper(ProductMapper.class).select(productId);
	}
	
	@Override
	public void insert(ProductEntity product) {
		sqlSession.getMapper(ProductMapper.class).insert(product);
	}

	@Override
	public void update(ProductEntity product) {
		sqlSession.getMapper(ProductMapper.class).update(product);
	}

	@Override
	public void delete(ProductEntity product) {
		sqlSession.getMapper(ProductMapper.class).delete(product);
		
	}


}

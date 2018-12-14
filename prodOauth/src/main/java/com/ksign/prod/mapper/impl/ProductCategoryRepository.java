package com.ksign.prod.mapper.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksign.prod.entity.ProductCategoryEntity;
import com.ksign.prod.mapper.ProductCategoryMapper;


@Repository("pcRepo")
public class ProductCategoryRepository implements ProductCategoryMapper {
	@Autowired SqlSessionTemplate sqlSession;

	@Override
	public ArrayList<ProductCategoryEntity> selectList(HashMap<String, Object> paramMap) {
		return sqlSession.getMapper(ProductCategoryMapper.class).selectList(paramMap);
	}

	@Override
	public int selectListCount(HashMap<String, Object> paramMap) {
		return sqlSession.getMapper(ProductCategoryMapper.class).selectListCount(paramMap);
	}

	@Override
	public ProductCategoryEntity select(String productCategoryId) {
		return sqlSession.getMapper(ProductCategoryMapper.class).select(productCategoryId);
	}
	
	@Override
	public ProductCategoryEntity selectByName(String name) {
		return sqlSession.getMapper(ProductCategoryMapper.class).selectByName(name);
	}

	@Override
	public void update(ProductCategoryEntity productCategory) {
		sqlSession.getMapper(ProductCategoryMapper.class).update(productCategory);
	}

	@Override
	public void insert(ProductCategoryEntity productCategory) {
		sqlSession.getMapper(ProductCategoryMapper.class).insert(productCategory);
	}

	@Override
	public void delete(ProductCategoryEntity productCategory) {
		sqlSession.getMapper(ProductCategoryMapper.class).delete(productCategory);
	}

}

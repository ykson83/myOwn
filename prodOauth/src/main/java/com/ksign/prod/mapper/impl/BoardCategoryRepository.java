package com.ksign.prod.mapper.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksign.prod.entity.BoardCategoryEntity;
import com.ksign.prod.mapper.BoardCategoryMapper;

@Repository("bcRepo")
public class BoardCategoryRepository implements BoardCategoryMapper {
	@Autowired SqlSessionTemplate sqlSession;

	@Override
	public ArrayList<BoardCategoryEntity> selectList(HashMap<String, Object> paramMap) {
		return sqlSession.getMapper(BoardCategoryMapper.class).selectList(paramMap);
	}

	@Override
	public int selectListCount(HashMap<String, Object> paramMap) {
		return sqlSession.getMapper(BoardCategoryMapper.class).selectListCount(paramMap);
	}

	@Override
	public BoardCategoryEntity select(String boardCategoryId) {
		return sqlSession.getMapper(BoardCategoryMapper.class).select(boardCategoryId);
	}
	
	@Override
	public BoardCategoryEntity selectByName(String name) {
		return sqlSession.getMapper(BoardCategoryMapper.class).selectByName(name);
	}

	@Override
	public void update(BoardCategoryEntity boardCategory) {
		sqlSession.getMapper(BoardCategoryMapper.class).update(boardCategory);
	}

	@Override
	public void insert(BoardCategoryEntity boardCategory) {
		sqlSession.getMapper(BoardCategoryMapper.class).insert(boardCategory);
	}

	@Override
	public void delete(BoardCategoryEntity boardCategory) {
		sqlSession.getMapper(BoardCategoryMapper.class).delete(boardCategory);
	}
	
	
}

package com.ksign.prod.mapper.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksign.prod.entity.BoardEntity;
import com.ksign.prod.mapper.BoardMapper;

@Repository("boardRepo")
public class BoardRepository implements BoardMapper {
	@Autowired SqlSessionTemplate sqlSession;

	@Override
	public ArrayList<BoardEntity> selectList(HashMap<String, Object> paramMap) {
		return sqlSession.getMapper(BoardMapper.class).selectList(paramMap);
	}

	@Override
	public int selectListCount(HashMap<String, Object> paramMap) {
		return sqlSession.getMapper(BoardMapper.class).selectListCount(paramMap);
	}

	@Override
	public BoardEntity select(String boardId) {
		return sqlSession.getMapper(BoardMapper.class).select(boardId);
	}

	@Override
	public void update(BoardEntity board) {
		sqlSession.getMapper(BoardMapper.class).update(board);
	}

	@Override
	public void insert(BoardEntity board) {
		sqlSession.getMapper(BoardMapper.class).insert(board);
	}

	@Override
	public void delete(BoardEntity board) {
		sqlSession.getMapper(BoardMapper.class).delete(board);
	}

}

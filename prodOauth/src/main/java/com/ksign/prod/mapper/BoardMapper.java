package com.ksign.prod.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.ksign.prod.entity.BoardEntity;

public interface BoardMapper {
	public ArrayList<BoardEntity> selectList(HashMap<String, Object> paramMap);
	public int selectListCount(HashMap<String, Object> paramMap);
	public BoardEntity select(String boardId);
	public void update(BoardEntity board);
	public void insert(BoardEntity board);
	public void delete(BoardEntity board);
}

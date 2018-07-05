package com.ksign.prod.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.ksign.prod.entity.BoardCategoryEntity;

public interface BoardCategoryMapper {
	public ArrayList<BoardCategoryEntity> selectList(HashMap<String, Object> paramMap);
	public int selectListCount(HashMap<String, Object> paramMap);
	public BoardCategoryEntity select(String boardCategoryId);
	public BoardCategoryEntity selectByName(String name);
	public void update(BoardCategoryEntity boardCategory);
	public void insert(BoardCategoryEntity boardCategory);
	public void delete(BoardCategoryEntity boardCategory);
}

package com.ksign.prod.mapper.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksign.prod.entity.DownloadHistoryEntity;
import com.ksign.prod.entity.ProductEntity;
import com.ksign.prod.mapper.DownloadHistoryMapper;

@Repository("dhRepo")
public class DownloadHistoryRepository implements DownloadHistoryMapper {
	@Autowired SqlSessionTemplate sqlSession;

	
	@Override
	public ArrayList<ProductEntity> selectList(HashMap<String, Object> paramMap) {
		return sqlSession.getMapper(DownloadHistoryMapper.class).selectList(paramMap);
	}

	@Override
	public int selectListCount(HashMap<String, Object> paramMap) {
		return sqlSession.getMapper(DownloadHistoryMapper.class).selectListCount(paramMap);
	}

	@Override
	public DownloadHistoryEntity select(String historyId) {
		return sqlSession.getMapper(DownloadHistoryMapper.class).select(historyId);
	}

	@Override
	public void insert(DownloadHistoryEntity downloadHistory) {
		sqlSession.getMapper(DownloadHistoryMapper.class).insert(downloadHistory);
	}


}

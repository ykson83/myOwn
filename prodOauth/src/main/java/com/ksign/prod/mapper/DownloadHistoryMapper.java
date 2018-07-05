package com.ksign.prod.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.ksign.prod.entity.DownloadHistoryEntity;
import com.ksign.prod.entity.ProductEntity;

public interface DownloadHistoryMapper {
	public ArrayList<ProductEntity> selectList(HashMap<String, Object> paramMap);
	public int selectListCount(HashMap<String, Object> paramMap);
	public DownloadHistoryEntity select(String historyId);
	public void insert(DownloadHistoryEntity downloadHistory);
}

package com.ksign.prod.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksign.prod.dto.DataTableVO;
import com.ksign.prod.entity.BoardCategoryEntity;
import com.ksign.prod.mapper.impl.BoardCategoryRepository;
import com.ksign.prod.util.SequenceUtil;

@Service("boardCategoryServ")
public class BoardCategoryService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired BoardCategoryRepository bCateRepo;
	@Autowired SequenceUtil seqUtil;
	
	public DataTableVO listWithCount(HashMap<String, Object> param) {
		DataTableVO table = new DataTableVO();
		
		table.setRecordsFiltered(bCateRepo.selectListCount(param));
		table.setData(bCateRepo.selectList(param));
		
		return table;
	}
	
	public Object select(String objectId) {
		return bCateRepo.select(objectId);
	}
	
	public Object selectByName(String name) {
		BoardCategoryEntity _p = bCateRepo.selectByName(name);
		
		if(_p != null) {
			return _p;
		} else {
			return null;
		}
	}

	@Transactional
	public void insert(BoardCategoryEntity bCate) {
		String boardCategoryId = seqUtil.getNextId("boardCategory", "BC", 12);
		bCate.setBoardCategoryId(boardCategoryId);
		
		BoardCategoryEntity _p = bCateRepo.select(bCate.getBoardCategoryId());
		
		if(_p == null) {
			bCateRepo.insert(bCate);
		} else {
			throw new IllegalStateException("already exists");
		}
	}

	@Transactional
	public void update(BoardCategoryEntity bCate) {//존재하면? update
		BoardCategoryEntity bCatetForCheck = bCateRepo.select(bCate.getBoardCategoryId());
		if(bCatetForCheck != null) {
			bCateRepo.update(bCate);
		} else {
			throw new IllegalStateException("cannot find object");
		}
	}
	
	@Transactional
	public void delete(BoardCategoryEntity bCate) {//존재하면? delete
		BoardCategoryEntity _p = bCateRepo.select(bCate.getBoardCategoryId());
		if(_p != null) {
			bCateRepo.delete(bCate);
		} else {
			throw new IllegalStateException("cannot find object");
		}
	}
}

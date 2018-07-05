package com.ksign.prod.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksign.prod.dto.DataTableVO;
import com.ksign.prod.entity.BoardEntity;
import com.ksign.prod.mapper.impl.BoardRepository;
import com.ksign.prod.util.SequenceUtil;

@Service("boardServ")
public class BoardService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired BoardRepository boardRepo;
	@Autowired SequenceUtil seqUtil;
	
	public DataTableVO listWithCount(HashMap<String, Object> param) {
		if(log.isDebugEnabled()) log.debug("get list: " + param.toString());
		DataTableVO table = new DataTableVO();
		
		table.setRecordsFiltered(boardRepo.selectListCount(param));
		table.setData(boardRepo.selectList(param));
		
		return table;
	}
	
	public Object select(String objectId) {
		return boardRepo.select(objectId);
	}

	@Transactional
	public void insert(BoardEntity board) {
		String boardId = seqUtil.getNextId("board", "B", 12);
		board.setBoardId(boardId);
		BoardEntity _p = boardRepo.select(boardId);
		
		if(_p == null) {
			boardRepo.insert(board);
		} else {
			throw new IllegalStateException("already exists"); 
		}
	}

	@Transactional
	public void update(BoardEntity board) {//존재하면? update
		BoardEntity boardForCheck = boardRepo.select(board.getBoardId());
		if(boardForCheck != null) {
			boardRepo.update(board);
		} else {
			throw new IllegalStateException("cannot find object");
		}
	}
	
	@Transactional
	public void delete(BoardEntity board) {//존재하면? delete
		BoardEntity _p = boardRepo.select(board.getBoardId());
		
		if(_p != null) {
			boardRepo.delete(board);
		} else {
			throw new IllegalStateException("cannot find object");
		}
	}
}
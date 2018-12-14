package com.ksign.prod.service;

import java.util.HashMap;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksign.prod.dto.DataTableVO;
import com.ksign.prod.entity.DownloadHistoryEntity;
import com.ksign.prod.mapper.impl.DownloadHistoryRepository;
import com.ksign.prod.util.SequenceUtil;

@Service("downloadHistoryServ")
public class DownloadHistoryService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired DownloadHistoryRepository dhRepo;
	@Autowired SequenceUtil seqUtil;
	
	public DataTableVO listWithCount(HashMap<String, Object> param) {
		DataTableVO table = new DataTableVO();
		
		table.setRecordsFiltered(dhRepo.selectListCount(param));
		table.setData(dhRepo.selectList(param));
		
		return table;
	}
	
	public Object select(String historyId) {
		return dhRepo.select(historyId);
	}

	@Transactional
	public String insert(DownloadHistoryEntity downloadHistory) {
		String historyId = seqUtil.getNextId("downloadHistory", "DH", 12);
		downloadHistory.setHistoryId(historyId);
		
		DownloadHistoryEntity _p = dhRepo.select(downloadHistory.getHistoryId());
		if(_p == null) {//중복 없으면 insert
			dhRepo.insert(downloadHistory);
			
			String ticket = UUID.randomUUID().toString();
			downloadHistory.setTicket(ticket);
			
			return ticket;
		} else {
			return null;
		}
	}
}
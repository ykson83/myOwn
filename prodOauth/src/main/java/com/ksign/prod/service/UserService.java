package com.ksign.prod.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ksign.prod.auth.UserPasswordEncoder;
import com.ksign.prod.dto.DataTableVO;
import com.ksign.prod.entity.BaseEntity;
import com.ksign.prod.entity.UserEntity;
import com.ksign.prod.mapper.impl.UserRepository;

@Service("userServ")
public class UserService {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired UserRepository userRepo;
	@Autowired UserPasswordEncoder passwordEncoder;
	
	public DataTableVO listWithCount(HashMap<String, Object> param) {
		if(log.isDebugEnabled()) log.debug("get list: " + param.toString());
		DataTableVO table = new DataTableVO();
		
		table.setRecordsFiltered(userRepo.selectListCount(param));
		table.setData(userRepo.selectList(param));
		
		return table;
	}
	
	public BaseEntity select(String objectId) {
		return userRepo.select(objectId);
	}

	@Transactional
	public void insert(UserEntity user) {
		UserEntity _p = userRepo.select(user.getUserId());
		
		if(_p == null) {//중복 없으면 insert
			userRepo.insert(user);
		} else {
			throw new IllegalStateException("already exists");
		}
	}

	@Transactional
	public void delete(UserEntity user) {//존재하면? delete
		UserEntity _p = userRepo.select(user.getUserId());
		
		if(_p != null) {
			userRepo.delete(user);
		} else {
			throw new IllegalStateException("cannot find object");
		}
	}

	@Transactional
	public void update(UserEntity user) {//존재하면? update
		UserEntity _p = userRepo.select(user.getUserId());
		
		if(_p != null) {
			if(StringUtils.isEmpty(user.getPassword())) {
				user.setPassword(_p.getPassword());
			} else {
				String passwd = passwordEncoder.encode(user.getPassword());
				user.setPassword(passwd);
			}
			userRepo.update(user);
		} else {
			throw new IllegalStateException("cannot find object");
		}
	}
}
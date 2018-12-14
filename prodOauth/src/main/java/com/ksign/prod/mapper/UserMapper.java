package com.ksign.prod.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.ksign.prod.entity.UserEntity;

public interface UserMapper {
	public ArrayList<UserEntity> selectList(HashMap<String, Object> paramMap);
	public int selectListCount(HashMap<String, Object> paramMap);
	public UserEntity select(String userId);
	public void update(UserEntity user);
	public void insert(UserEntity user);
	public void delete(UserEntity user);
}

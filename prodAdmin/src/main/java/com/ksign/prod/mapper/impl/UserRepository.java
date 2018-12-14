package com.ksign.prod.mapper.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksign.prod.entity.UserEntity;
import com.ksign.prod.mapper.UserMapper;

@Repository("userRepo")
public class UserRepository implements UserMapper {
	@Autowired SqlSessionTemplate sqlSession;
	
	@Override
	public ArrayList<UserEntity> selectList(HashMap<String, Object> paramMap) {
		return sqlSession.getMapper(UserMapper.class).selectList(paramMap);
	}

	@Override
	public int selectListCount(HashMap<String, Object> paramMap) {
		return sqlSession.getMapper(UserMapper.class).selectListCount(paramMap);
	}

	@Override
	public UserEntity select(String userId) {
		return sqlSession.getMapper(UserMapper.class).select(userId);
	}

	@Override
	public void update(UserEntity user) {
		sqlSession.getMapper(UserMapper.class).update(user);
	}

	@Override
	public void insert(UserEntity user) {
		sqlSession.getMapper(UserMapper.class).insert(user);
	}

	@Override
	public void delete(UserEntity user) {
		sqlSession.getMapper(UserMapper.class).delete(user);
	}

}

package com.ksign.prod.mapper.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksign.prod.entity.SequenceEntity;
import com.ksign.prod.mapper.SequenceMapper;

@Repository("seqRepo")
public class SequenceRepository implements SequenceMapper {
	@Autowired SqlSessionTemplate sqlSession;
	
	@Override
	public SequenceEntity select(String name) {
		return sqlSession.getMapper(SequenceMapper.class).select(name);
	}

	@Override
	public void update(SequenceEntity seq) {
		sqlSession.getMapper(SequenceMapper.class).update(seq);		
	}

	@Override
	public void insert(SequenceEntity seq) {
		sqlSession.getMapper(SequenceMapper.class).insert(seq);		
	}
}

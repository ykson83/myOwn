package com.ksign.prod.mapper;

import com.ksign.prod.entity.SequenceEntity;

public interface SequenceMapper {
	public SequenceEntity select(String name);
	public void update(SequenceEntity seq);
	public void insert(SequenceEntity seq);
}

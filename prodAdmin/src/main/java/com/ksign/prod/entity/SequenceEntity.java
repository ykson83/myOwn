package com.ksign.prod.entity;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("Sequence")
public class SequenceEntity {
	private String name;
	private long seqValue;
}

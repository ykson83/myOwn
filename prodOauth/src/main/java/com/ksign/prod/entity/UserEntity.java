package com.ksign.prod.entity;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("UserVO")
public class UserEntity extends BaseEntity {
	private static final long serialVersionUID = 6272737983799954233L;

	private String userId;
	private String name;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String dept;
	private int type;
	
}

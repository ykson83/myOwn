package com.ksign.prod.entity;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Alias("BoardCategory")
public class BoardCategoryEntity extends BaseEntity {
	private static final long serialVersionUID = 8331408807538342717L;
	
	private String boardCategoryId;
	private String name;

}

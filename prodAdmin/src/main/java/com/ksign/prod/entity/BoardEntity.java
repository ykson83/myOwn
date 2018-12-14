package com.ksign.prod.entity;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Alias("Board")
public class BoardEntity extends BaseEntity {
	private static final long serialVersionUID = 781326256444609990L;
	
	private String boardId;
	private String title;
	
	//private String boardCategoryId;
	private BoardCategoryEntity boardCategory; //FK
}

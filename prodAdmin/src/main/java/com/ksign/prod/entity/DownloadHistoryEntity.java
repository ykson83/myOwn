package com.ksign.prod.entity;


import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Alias("DownloadHistory")
public class DownloadHistoryEntity extends BaseEntity{
	private static final long serialVersionUID = 3036990086574323849L;

	private String historyId;
	private String purpose;
	
	private String ticket;
	
	private ProductEntity product;
}

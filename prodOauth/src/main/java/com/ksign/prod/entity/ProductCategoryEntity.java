package com.ksign.prod.entity;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Alias("ProductCategory")
public class ProductCategoryEntity extends BaseEntity {
	private static final long serialVersionUID = 1160940372134679167L;
	
	private String productCategoryId;
	private String name;

}

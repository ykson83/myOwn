package com.ksign.prod.entity;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Alias("Product")
public class ProductEntity extends BaseEntity {
	private static final long serialVersionUID = -409268406170649268L;
	
	private String productId;
	private String name;
	private int type;
	
	private String version; 
	private String fileName;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private byte[] file;
	
	private ProductCategoryEntity productCategory;
}

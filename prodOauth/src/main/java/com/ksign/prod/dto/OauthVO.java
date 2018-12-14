package com.ksign.prod.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OauthVO {
	
	String code;
	String access_token;
	String token_type;
	int expires_in;
	String id_token;

}

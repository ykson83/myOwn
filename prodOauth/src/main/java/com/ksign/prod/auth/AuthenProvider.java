package com.ksign.prod.auth;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class AuthenProvider implements AuthenticationProvider, Serializable{
	private static final long serialVersionUID = -1721043305390884858L;
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired AuthenService authenService;
	@Autowired UserPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authen) throws AuthenticationException {
		//authen에서 가져온 userId, password
		String userId = authen.getName();
		String password = (String) authen.getCredentials();
		
		//authenService에서 userEntity 가져와서 비밀번호 비교
		
		LoginToken loginToken = (LoginToken) authenService.loadUserByUsername(userId);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		//loginToken이 null이면 에러메시지 throw
		if(StringUtils.isEmpty(loginToken.getPassword())) {
			throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다");
			
		} else {
			if(passwordEncoder.matches(password, loginToken.getPassword())) {
				authorities.add(new GrantedAuthority() {
					private static final long serialVersionUID = 1486036945570168427L;
					@Override
					public String getAuthority() {
						return "ROLE_USER";
					}
				});
			} else {
				throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다");
			}
		}
		
		//사용자의 status 체크
		if(!loginToken.isEnabled()) {
			throw new BadCredentialsException("사용 정지 상태입니다");
		}
		
		return new UsernamePasswordAuthenticationToken(loginToken, password, authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
}
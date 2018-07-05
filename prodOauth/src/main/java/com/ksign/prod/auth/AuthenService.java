package com.ksign.prod.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ksign.prod.entity.UserEntity;
import com.ksign.prod.service.UserService;

@Service
public class AuthenService implements UserDetailsService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserEntity userEntity = (UserEntity) userService.select(userId);
		
		if(userEntity == null) {
			userEntity = new UserEntity();
		}
		
		LoginToken loginToken = new LoginToken(userEntity);
		return loginToken;
	}
}
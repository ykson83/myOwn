package com.ksign.prod.auth;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ksign.prod.ctx.ConstantContext;
import com.ksign.prod.entity.UserEntity;

public class LoginToken implements UserDetails, Serializable {
	private static final long serialVersionUID = 3696550159168915125L;
	
	private UserEntity userEntity;
	public LoginToken(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userEntity.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return userEntity.getStatus() != ConstantContext.STATUS_OFF;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		return userEntity.getStatus() == ConstantContext.STATUS_ON;
	}
	
//	@Override
//	public boolean isEnabled() {
//		if(userEntity.getStatus() == 1) {
//			return false;
//		} else {
//			return true;
//		}
//	}
}

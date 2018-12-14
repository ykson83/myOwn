package com.ksign.prod.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordEncoder implements PasswordEncoder {

	private static final byte[] _SALT = "thsdusruddlaksemfdjTdjdy123982346598".getBytes();
	
	@Override
	public String encode(CharSequence rawPassword) {
		String result = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(_SALT);
			byte[] pwd = md.digest(rawPassword.toString().getBytes());//byte로 변환

			result = new String(Base64.encode(pwd));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encodedPassword.equals(encode(rawPassword));
	}
}
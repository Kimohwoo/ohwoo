package com.ohwoo.domain;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomPasswordEncoder implements PasswordEncoder {
	
	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		log.warn("before encode : " + rawPassword);
		return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(8));
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		log.warn("matches : " + rawPassword + " : " + encodedPassword);
		
		return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
	}

}

package com.ohwoo.mapper;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {
	
	@Test
	public void test() {
		
		String password = "kkkk";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		passwordEncoder = new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
	}

}

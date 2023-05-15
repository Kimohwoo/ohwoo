package com.ohwoo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ohwoo.DTO.UserDTO;
import com.ohwoo.mapper.UserMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService{

	@Setter(onMethod_=@Autowired)
	private UserMapper userMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.warn("Load User BY UserName : " + username);
		
		UserDTO user = userMapper.read(username);
		log.warn("queried By user Mapper : " + user);
		
		return user == null? null : new CustomUser(user);
	}

}

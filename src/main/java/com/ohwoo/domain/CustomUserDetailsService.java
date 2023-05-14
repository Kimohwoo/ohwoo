package com.ohwoo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ohwoo.DTO.CustomUserDTO;
import com.ohwoo.DTO.UserDTO;
import com.ohwoo.mapper.UserMapper;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDTO user = mapper.read(username);
		CustomUserDTO cuser = new CustomUserDTO(user);
		return user == null ? null : cuser;
	}

}

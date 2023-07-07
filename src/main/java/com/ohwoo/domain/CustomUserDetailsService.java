package com.ohwoo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ohwoo.DTO.UserDTO;
import com.ohwoo.Service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.warn("Load User BY UserName : " + username);

		UserDTO user = userService.login(username);
		log.warn("queried By user Mapper : " + user);

		return user == null ? null : new CustomUserDetails(user);
	}

}
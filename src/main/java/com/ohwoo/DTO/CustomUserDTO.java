package com.ohwoo.DTO;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUserDTO extends User {

	private UserDTO user;

	public CustomUserDTO(UserDTO user) {
		super(user.getId(), user.getPassword(), user.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
		this.user = user;
		// TODO Auto-generated constructor stub
	}

}

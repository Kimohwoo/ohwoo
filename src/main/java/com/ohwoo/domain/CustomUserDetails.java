package com.ohwoo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ohwoo.DTO.UserDTO;

import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Log4j
@ToString
public class CustomUserDetails implements UserDetails {

	private UserDTO user;

	public CustomUserDetails(UserDTO user) {
		this.user = user;
	}

	public UserDTO getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴한다. ( true : 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정이 감져있지 않았는지 리턴한다. ( true : 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	// 비밀번호가 만료되지 않았는지 리턴한다. ( true : 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정 활성화(사용가능)인지 리턴한다. ( true : 활성화)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(() -> {
			return "ROLE_" + user.getAuthList();
		}); // add에 들어올 파라미터는 GrantedAuthority밖에 없으니
		log.info(authorities);
		return authorities;
	}
}

package com.ohwoo.mapper;

import com.ohwoo.DTO.UserDTO;

public interface UserMapper {

	public UserDTO read(String id);
	
	public void regist(UserDTO user);

}

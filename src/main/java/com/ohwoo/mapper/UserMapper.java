package com.ohwoo.mapper;

import com.ohwoo.DTO.AuthDTO;
import com.ohwoo.DTO.UserDTO;

public interface UserMapper {

	public UserDTO read(String username);

	public String idCheck(String username);

	public int regist(UserDTO user);

	public void registAuth(AuthDTO auth);

	public int modify(UserDTO user);

	public int delete(UserDTO user);

}

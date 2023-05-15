package com.ohwoo.Service;

import javax.servlet.http.HttpSession;

import com.ohwoo.DTO.UserDTO;

public interface UserService {

	public void register(UserDTO user, HttpSession session);

//	public UserDTO login(UserDTO user, HttpSession session);
//
//	public void logout(HttpSession session);

	public UserDTO get(String id);

	public boolean modify(UserDTO user);

	public boolean delete(UserDTO user);

}

package com.ohwoo.Service;

import javax.servlet.http.HttpSession;

import com.ohwoo.DTO.UserDTO;

public interface UserService {

	public void register(UserDTO user, HttpSession session);

	public String IdCheck(String username);

	public UserDTO get(UserDTO user);

	public boolean modify(UserDTO user);

	public boolean delete(UserDTO user);

}

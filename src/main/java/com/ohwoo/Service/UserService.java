package com.ohwoo.Service;

import com.ohwoo.DTO.UserDTO;

public interface UserService {

	public boolean register(UserDTO user);

	public String IdCheck(String username);

	public UserDTO login(String username);

	public boolean modify(UserDTO user);

	public boolean delete(UserDTO user);

}

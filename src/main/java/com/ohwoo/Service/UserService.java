package com.ohwoo.Service;

import com.ohwoo.DTO.UserDTO;

public interface UserService {

	public void register(UserDTO user);

	public String IdCheck(String username);

	public UserDTO login(UserDTO user);

	public boolean modify(UserDTO user);

	public boolean delete(UserDTO user);

}

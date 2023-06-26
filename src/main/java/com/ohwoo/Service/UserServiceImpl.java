package com.ohwoo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ohwoo.DTO.AuthDTO;
import com.ohwoo.DTO.UserDTO;
import com.ohwoo.mapper.UserMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder customEncoder;

	@Override
	public boolean register(UserDTO user) {
		// TODO Auto-generated method stub
		user.setPassword(customEncoder.encode(user.getPassword()));
		log.info("유저 비밀번호 인코딩 확인 : " + user.getPassword());
		if(userMapper.regist(user) == 1) {
			log.info("유저 만들어지는지 확인" + user);
			List<AuthDTO> auth = new ArrayList<AuthDTO>();
			AuthDTO auth2 = new AuthDTO();
			auth2.setUsername(user.getUsername());
			auth2.setAuth("ROLE_USER");
			auth.add(auth2);
			user.setAuthList(auth);
			userMapper.registAuth(auth2);
			return true;
		}
		return false;
	}

	@Override
	public UserDTO login(UserDTO user) {
		// TODO Auto-generated method stub
		log.info("유저 Login" + user);
		user.setPassword(customEncoder.encode(user.getPassword()));
		log.info("인코딩 확인하기" + user.getPassword());
		return userMapper.read(user.getUsername());
	}

	@Override
	public boolean modify(UserDTO user) {
		// TODO Auto-generated method stub
		log.info(user + "수정");
		return userMapper.modify(user) == 1;
	}

	@Override
	public boolean delete(UserDTO user) {
		// TODO Auto-generated method stub
		log.info(user + "삭제");
		return userMapper.delete(user) == 1;
	}

	@Override
	public String IdCheck(String username) {
		// TODO Auto-generated method stub
		String getName = userMapper.idCheck(username);
		if (getName.equals(username)) {
			return "Already Use";
		} else {
			return "Not Use";
		}
	}

}

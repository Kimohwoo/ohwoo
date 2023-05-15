package com.ohwoo.Service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.ohwoo.DTO.UserDTO;
import com.ohwoo.mapper.UserMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	@Override
	public void register(UserDTO user, HttpSession session) {
		// TODO Auto-generated method stub
		log.info(user + "등록합니다");
		userMapper.regist(user);
		session.setAttribute("sessionId", user.getId());
	}

	@Override
	public UserDTO get(String id) {
		// TODO Auto-generated method stub
		log.info(id + "유저 Id");
		return userMapper.read(id);
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

}

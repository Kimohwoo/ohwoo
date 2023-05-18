package com.ohwoo.Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.ohwoo.DTO.AuthDTO;
import com.ohwoo.DTO.UserDTO;
import com.ohwoo.domain.CustomPasswordEncoder;
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
		CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();
		user.setLevel("4등급");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		log.info("유저 비밀번호 인코딩 확인 : " + user.getPassword());
		List<AuthDTO> auth = new ArrayList<AuthDTO>();
		AuthDTO auth2 = new AuthDTO();
		auth2.setUsername(user.getUsername());
		auth2.setAuth("USER");
		auth.add(auth2);
		user.setAuthList(auth);
		log.info("회원가입 최종확인용 : " + user);
		userMapper.regist(user);
		userMapper.registAuth(auth2);
		session.setAttribute("sessionId", user.getUsername());
	}

	@Override
	public UserDTO get(UserDTO user) {
		// TODO Auto-generated method stub
		log.info(user + "유저 get");
		UserDTO user2 = new UserDTO();
		CustomPasswordEncoder passwordEncoder = new CustomPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
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
			return "사용 불가능한 아이디 입니다.";
		} else {
			return "사용 가능한 아이디 입니다.";
		}
	}

}

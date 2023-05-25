package com.ohwoo.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ohwoo.DTO.UserDTO;
import com.ohwoo.Service.UserService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration(classes = { com.ohwoo.Config.RootConfig.class, com.ohwoo.Config.SecurityConfig.class })
public class UserServiceTest {

	@Autowired
	private UserService service;
	@Autowired
	private PasswordEncoder customEncoder;

//	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}

	@Test
	public void testGet() {
		UserDTO user = new UserDTO();
		user.setUsername("111");
		user.setPassword("111");
		log.info(service.login(user));
	}

//	@Test
	public void testRegister() {
		UserDTO user = new UserDTO();
		user.setUsername("111222");
		user.setPassword("111222");
		user.setName("service");
		user.setNickName("Service");
		user.setAddress("1009274jdd");
		user.setPhone("010-1111-2222");
		user.setLevel("4등급");

		service.register(user);
		user = service.login(user);
		log.info("생성된 유저 : " + user);
	}

}

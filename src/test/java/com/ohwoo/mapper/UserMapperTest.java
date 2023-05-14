package com.ohwoo.mapper;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ohwoo.DTO.UserDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { com.ohwoo.Config.RootConfig.class })
@Log4j
public class UserMapperTest {

	@Setter(onMethod_ = @Autowired)
	private UserMapper mapper;

//	@Test
	public void testGetList() {
		mapper.read("1");
	}
	
	@Test
	public void testInsert() {
		UserDTO user = new UserDTO();
		user.setId("1111");
		user.setPassword("1111");
		user.setNickName("Kim");
		user.setAddress("1111");
		user.setName("kim");
		user.setPhone("0000");
		user.setLevel("1");
		mapper.regist(user);
	}


}
package com.ohwoo.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ohwoo.DTO.AuthDTO;
import com.ohwoo.DTO.UserDTO;
import com.ohwoo.Service.UserService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { com.ohwoo.Config.RootConfig.class, com.ohwoo.Config.SecurityConfig.class })
@Log4j
public class UserMapperTest {

	@Setter(onMethod_ = @Autowired)
	private UserMapper mapper;
	@Setter(onMethod_ = @Autowired)
	private UserService service;
	@Setter(onMethod_ = @Autowired)
	private DataSource ds;
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder passwordEncoder;

	@Test
	public void testGetList() {
		UserDTO user = new UserDTO();
		user.setUsername("user2");
		user = service.login(user);
		log.info("유저 확인" + user);
		user.getAuthList().forEach(authDTO -> log.info(authDTO));
	}

//	@Test
	public void testIdCheck() {

		String username = "0011";
		log.info(username);
		log.info("idCheck 확인용 : " + mapper.idCheck(username));

	}

//	@Test
	public void testInsert() {
		UserDTO user = new UserDTO();

		user.setUsername("user2");
		user.setPassword(passwordEncoder.encode("user2"));
		user.setAddress("주소");
		user.setLevel("4");
		user.setName("kimSi");
		user.setNickName("닉네임");
		user.setPhone("010-1234-5678");

		List<AuthDTO> auth = new ArrayList<AuthDTO>();
		AuthDTO auth2 = new AuthDTO();
		auth2.setUsername(user.getUsername());
		auth2.setAuth("ROLE_USER");
		auth.add(auth2);
		user.setAuthList(auth);

		service.register(user);

	}

//	@Test
	public void testInsertUser() {

		String sql = " INSERT INTO user(id, password, name) VALUES (?,?,?)  ";
		for (int i = 0; i < 100; i++) {

			Connection con = null;
			PreparedStatement ps = null;

			try {
				con = ds.getConnection();
				ps = con.prepareStatement(sql);

				ps.setString(2, passwordEncoder.encode("pw" + i));
				if (i < 80) {
					ps.setString(1, "user" + i);
					ps.setString(3, "일반사용자" + i);
				} else if (i < 90) {
					ps.setString(1, "manager" + i);
					ps.setString(3, "운영자" + i);
				} else {
					ps.setString(1, "admin" + i);
					ps.setString(3, "관리자" + i);
				}
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}

	}

//	@Test
	public void testInsertUserAuth() {

		String sql = " INSERT INTO user_auth(id, auth) VALUES (?,?)  ";
		for (int i = 0; i < 100; i++) {

			Connection con = null;
			PreparedStatement ps = null;

			try {
				con = ds.getConnection();
				ps = con.prepareStatement(sql);

				if (i < 80) {
					ps.setString(1, "user" + i);
					ps.setString(2, "ROLE_USER");
				} else if (i < 90) {
					ps.setString(1, "manager" + i);
					ps.setString(2, "ROLE_MEMBER");
				} else {
					ps.setString(1, "admin" + i);
					ps.setString(2, "ROLE_ADMIN");
				}
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}

	}

}
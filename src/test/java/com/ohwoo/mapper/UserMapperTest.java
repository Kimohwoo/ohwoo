package com.ohwoo.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ohwoo.DTO.UserDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { com.ohwoo.Config.RootConfig.class, com.ohwoo.Config.SecurityConfig.class })
@Log4j
public class UserMapperTest {

	@Setter(onMethod_ = @Autowired)
	private UserMapper mapper;
	@Setter(onMethod_=@Autowired)
	private DataSource ds;
	@Setter(onMethod_=@Autowired)
	private BCryptPasswordEncoder passwordEncoder;
	

	@Test
	public void testGetList() {
		UserDTO user = mapper.read("admin99");
		log.info(user);
		user.getAuthList().forEach(authDTO -> log.info(authDTO));
	}
	
//	@Test
	public void testInsertUser() {
		
		String sql = " INSERT INTO user(id, password, name) VALUES (?,?,?)  ";
		for(int i = 0; i<100; i++) {
			
			Connection con = null;
			PreparedStatement ps = null;
			
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				
				ps.setString(2, passwordEncoder.encode("pw" + i));
				if(i<80) {
					ps.setString(1, "user" + i);
					ps.setString(3, "일반사용자" + i);
				} else if(i < 90) {
					ps.setString(1, "manager" + i);
					ps.setString(3, "운영자" + i);
				} else {
					ps.setString(1, "admin" + i);
					ps.setString(3, "관리자" + i);
				}
				ps.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(ps != null) {try{ps.close();} catch(Exception e) {e.printStackTrace();}}
				if(con != null) {try{con.close();} catch(Exception e) {e.printStackTrace();}}
			}
			
		}
		
	}
	
//	@Test
	public void testInsertUserAuth() {
		
		String sql = " INSERT INTO user_auth(id, auth) VALUES (?,?)  ";
		for(int i = 0; i<100; i++) {
			
			Connection con = null;
			PreparedStatement ps = null;
			
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(sql);
				
				if(i<80) {
					ps.setString(1, "user" + i);
					ps.setString(2, "ROLE_USER");
				} else if(i < 90) {
					ps.setString(1, "manager" + i);
					ps.setString(2, "ROLE_MEMBER");
				} else {
					ps.setString(1, "admin" + i);
					ps.setString(2, "ROLE_ADMIN");
				}
				ps.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if(ps != null) {try{ps.close();} catch(Exception e) {e.printStackTrace();}}
				if(con != null) {try{con.close();} catch(Exception e) {e.printStackTrace();}}
			}
			
		}
		
	}
	
	
//	@Test
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
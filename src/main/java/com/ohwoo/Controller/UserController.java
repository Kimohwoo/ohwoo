package com.ohwoo.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohwoo.DTO.UserDTO;
import com.ohwoo.Service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user/*")
@AllArgsConstructor
@Log4j
public class UserController {

	private UserService userService;

	@GetMapping("/login")
	public void getLogin() {
	}

	@PostMapping("/login")
	public String login(UserDTO user, HttpSession session) {
		log.info("로그인 : " + user);
		user = userService.login(user);
		return "/";
	}

	@PostMapping("/id")
	@ResponseBody
	public String checkId(String username) {

		log.info("idCheck" + username);
		return userService.IdCheck(username);
	}

	@GetMapping("/regist")
	public void addUser() {
		log.info("addUser");
	}

	@PostMapping("/regist")
	public void addUser1(UserDTO user, HttpSession session) {
		log.info("회원가입" + user);
		userService.register(user);
		session.setAttribute("sessionId", user.getUsername());
	}

	@GetMapping("/modify")
	public void modifyUser() {

	}

	@PostMapping("/modify")
	public void modify() {

	}

	@PostMapping("/remove")
	@ResponseBody
	public void remove() {

	}

}

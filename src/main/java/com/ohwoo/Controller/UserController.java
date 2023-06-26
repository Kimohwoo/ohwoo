package com.ohwoo.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ohwoo.DTO.UserDTO;
import com.ohwoo.Service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user/*")
@AllArgsConstructor
@Log4j
public class UserController {

	private final UserService userService;

//	@PostMapping("/login")
//	public String login(UserDTO user, HttpSession session) {
//		log.info("로그인 : " + user);
//		user = userService.login(user);
//
//		return "/";
//	}

	@PostMapping("/{id}")
	public String checkId(String username) {
		log.info("idCheck" + username);
		return userService.IdCheck(username);
	}

	@GetMapping("/user-reg")
	public ModelAndView addUser() {
		ModelAndView mv = new ModelAndView("customLogin");
		return mv;
	}

	@PostMapping("/addUser")
	public UserDTO addUser1(UserDTO user, HttpSession session) {
		log.info("회원가입" + user);
		if(userService.register(user)){
			session.setAttribute("sessionId", user.getUsername());
			return userService.login(user);
		}
		return null;
	}

	@PutMapping("/user")
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

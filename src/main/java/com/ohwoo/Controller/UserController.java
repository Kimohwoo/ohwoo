package com.ohwoo.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
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

	@PostMapping("/{id}")
	public String checkId(String username) {
		log.info("idCheck" + username);
		return userService.IdCheck(username);
	}

	@GetMapping("/user-reg")
	public ModelAndView addUser() {
		ModelAndView mv = new ModelAndView("/user/addUser");
		return mv;
	}

	@PostMapping("/user-reg")
	public UserDTO addUser1(UserDTO user) {
		log.info("회원가입" + user);
		if(userService.register(user)){
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

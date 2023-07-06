package com.ohwoo.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ohwoo.DTO.UserDTO;
import com.ohwoo.Service.UserService;
import com.ohwoo.domain.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/user/*")
@RequiredArgsConstructor
@Log4j
public class UserController {

	private final UserService userService;
	private final JwtTokenProvider jwtTokenProvider;

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
	
	@PostMapping("/login")
	public String login(@RequestBody UserDTO user) {
		log.info("user email = " + user.getUsername());
		UserDTO cuser = userService.login(user.getUsername());
		return jwtTokenProvider.createToken(cuser.getUsername(), cuser.getAuthList());
	}

	@PostMapping("/user-reg")
	public UserDTO addUser1(UserDTO user) {
		log.info("회원가입" + user);
		if(userService.register(user)){
			return userService.login(user.getUsername());
		}
		return null;
	}

	@PutMapping("/user")
	public void modifyUser() {

	}

	@DeleteMapping("/user")
	public void remove() {

	}

}

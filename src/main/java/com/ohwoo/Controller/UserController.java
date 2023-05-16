package com.ohwoo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public void member() {
		log.info("user컨트롤러");
		
	}

	@GetMapping("/admin")
	public void admin() {
		log.info("admin 컨트롤러");
	}
	
	@GetMapping("/addUser")
	public void addUser() {
		log.info("addUser");
	}
	
	@PostMapping("/addUser")
	public void addUser1() {
		
	}

}

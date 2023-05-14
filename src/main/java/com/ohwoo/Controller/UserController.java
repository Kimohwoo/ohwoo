package com.ohwoo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user/*")
@AllArgsConstructor
@Log4j
public class UserController {

	@GetMapping("/all")
	public void user() {
		log.info("all컨트롤러");
	}
	
	@GetMapping("/user")
	public void member() {
		log.info("user컨트롤러");
	}
	
	@GetMapping("/admin")
	public void admin() {
		log.info("admin 컨트롤러");
	}
	
}

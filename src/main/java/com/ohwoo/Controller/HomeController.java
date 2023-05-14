package com.ohwoo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class HomeController {

	@GetMapping("/")
	public String home() {
		log.info("home입니다");
		return "home";
	}
	
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		log.info("에러 : " + error);
		log.info("로그아웃 : " + logout);
		if(error != null) {
			model.addAttribute("error", "login Error Check your account");
		}
		if(logout != null) {
			model.addAttribute("logout", "Logout!");
		}
		
	}
	
}

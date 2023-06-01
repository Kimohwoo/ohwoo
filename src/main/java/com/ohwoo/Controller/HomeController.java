package com.ohwoo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohwoo.DTO.User;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class HomeController {

	@GetMapping("/")
	public String home() {
		log.info("home입니다");
		return "home";
	}
	
	@PostMapping(value="android")
	@ResponseBody
	public String android(@RequestBody User user) {
		
		System.out.println("id: " + user.getId() + "password: " + user.getPassword());
		return "1";
	}

//	@GetMapping("/customLogin")
//	public void loginInput(String error, String logout, Model model) {
//		log.info("에러 : " + error);
//		log.info("로그아웃 : " + logout);
//		if(error != null) {
//			model.addAttribute("error", "login Error Check your account");
//		}
//		if(logout != null) {
//			model.addAttribute("logout", "Logout!");
//		}
//		
//	}

}

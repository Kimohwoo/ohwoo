package com.ohwoo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class HomeController {

	@GetMapping("/")
	public String home() {
		log.info("home입니다");
		return "index";
	}

	@GetMapping("/customLogin")
	public String login(){
		return "customLogin";
	}

}

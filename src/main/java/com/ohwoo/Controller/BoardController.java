package com.ohwoo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
@Log4j
public class BoardController {

	@GetMapping("/list")
	public void list() {
		log.info("list");

	}

	@GetMapping("/get")
	public void get() {
		log.info("get");
	}

}

package com.ohwoo.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ohwoo.DTO.VisitorDTO;
import com.ohwoo.Service.VisitorService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class HomeController {
	
	private final VisitorService visitorService;
	private final String VISITOR = "visitor";

	@GetMapping("/")
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
		log.info("home입니다");
		
		VisitorDTO visitor = new VisitorDTO();
		VisitorDTO nVisitor = new VisitorDTO();
		VisitorDTO allVisitor = new VisitorDTO();
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat.format(date);
		visitor.setRegDate(today);
		
		Cookie[] cookies = request.getCookies();
		boolean visitorExists = false;
		
		//기존 쿠키 확인
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(VISITOR)) {
					visitorExists = true;
					model.addAttribute("AllVisitor", visitorService.selectAllVisitor().getCount());
					model.addAttribute("TodayVisitor", visitorService.selectDayVisitor(visitor).getCount());
					break;
				}
			}
		}
		
		//쿠키가 없는경우
		if(!visitorExists) {
			//총 방문자
			
			if(visitorService.selectAllVisitor() == null) {
				log.info("selectAll = null");
				allVisitor.setCount(1);
			} else {
				log.info("selectAll = NotNull");
				allVisitor = visitorService.selectAllVisitor();			
			}
			log.info(allVisitor);
			model.addAttribute("AllVisitor", allVisitor.getCount());
			
			//금일 방문자
			log.info("금일 방문자 : " + visitor);
			visitor = visitorService.selectDayVisitor(visitor);
			log.info("방문자 확인 : " + visitor);
			if(visitor == null) {
				VisitorDTO newVisitor = new VisitorDTO();
				newVisitor.setCount(1);
				log.info("Insert 하기");
				visitorService.insertVisitor(newVisitor);
			} else {
				log.info("update 하기");
				visitorService.updateVisitor(visitor);
			}
			log.info("visiter 확인" + visitor);
			nVisitor.setRegDate(today);
			log.info("vister 날짜 입력 확인" + nVisitor);
			model.addAttribute("TodayVisitor", visitorService.selectDayVisitor(nVisitor).getCount());
			Cookie visitorCookie = new Cookie(VISITOR, "1");
			visitorCookie.setMaxAge(60 * 60 * 24);
			response.addCookie(visitorCookie);
			
		}
		
		return "index";
	}

	@GetMapping("/customLogin")
	public String login(){
		return "customLogin";
	}

}

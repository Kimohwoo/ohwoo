package com.ohwoo.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.warn("login Success");
		List<String> roleNames = new ArrayList<String>();
		
		auth.getAuthorities().forEach(
				authority -> {roleNames.add(authority.getAuthority());
				});
		log.warn("Role Names : " + roleNames);
		
		if(roleNames.contains("ROLE_USER")) {
			response.sendRedirect("/user/login");
			return;
		}
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/admin/");
			return;
		}
		
		response.sendRedirect("/");

	}

}

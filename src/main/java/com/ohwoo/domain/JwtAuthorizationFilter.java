package com.ohwoo.domain;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ohwoo.DTO.UserDTO;
import com.ohwoo.mapper.UserMapper;

import lombok.extern.log4j.Log4j;

@Log4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	@Autowired
	private UserMapper userMapper;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserMapper userMapper) {
		super(authenticationManager);
		this.userMapper = userMapper;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(JwtProperties.HEADER_STRING);
//        log.info(header + "__header부분_____JwtAuthorizationFilter__________");
		log.info("header 받아오는지 -> " + header);

		if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		String token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
		log.info("토큰값 : " + token);
		String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
				.getClaim("username").asString();
		log.info("username : " + username);
		if (username != null) {
			UserDTO user = userMapper.read(username);
			log.info("user" + user);
			CustomUserDetails customUserDetails = new CustomUserDetails(user);
			Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null,
					customUserDetails.getAuthorities());
			log.info("authentication => " + authentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		chain.doFilter(request, response);
	}
}
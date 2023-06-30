package com.ohwoo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ohwoo.domain.CustomPasswordEncoder;
import com.ohwoo.domain.CustomUserDetailsService;
import com.ohwoo.domain.JwtAuthenticationFilter;
import com.ohwoo.domain.JwtAuthorizationFilter;
import com.ohwoo.mapper.UserMapper;

import lombok.extern.log4j.Log4j;

@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserMapper userMapper;

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.addFilter(corsFilter()).csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 스프링시큐리티가 생성하지도않고 기존것을 사용하지도 않음->JWT 같은토큰방식을
																		// 쓸때 사용하는 설정
				.and().httpBasic().disable() // 사용자 인증방법으로는 HTTP Basic Authentication을 사용 안한다.
				.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()),
						UsernamePasswordAuthenticationFilter.class) // JwtAutienticationFilter : jwt를 사용해서 인증 처리
				.addFilterBefore(new JwtAuthorizationFilter(authenticationManager(), userMapper),
						UsernamePasswordAuthenticationFilter.class) // JwtAutiorizationFilter : jwt를 사용해서 인가 처리
//				.exceptionHandling().accessDeniedHandler(loginFailureHandler()).and
				.authorizeRequests().antMatchers("/user/user-reg").permitAll().antMatchers("/user/*")
				.access("hasAnyRole('USER','ADMIN')").antMatchers("/admin/*").access("hasRole('ADMIN')")
//				.antMatchers("/board/*").access("hasAnyAuthority('USER','ADMIN') or hasRole('USER','ADMIN')");
				.antMatchers("/board/*").access("hasRole('USER')");
		http.formLogin().loginPage("/customLogin");
//				.successHandler(loginSuccessHandler()).failureHandler(loginFailureHandler());
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
//		.invalidateHttpSession(true)
//				.deleteCookies("JSESSIONID");
	}

//
//	@Bean
//	public AuthenticationSuccessHandler loginSuccessHandler() {
//		return new CustomLoginSuccessHandler();
//	}
//
//	@Bean
//	public AuthenticationFailureHandler loginFailureHandler() {
//		return new CustomLoginFailureHandler();
//	}

	@Bean
	public UserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public PasswordEncoder customPasswordEncoder() {
		// customPasswordEncoder를 생성하고 반환
		return new CustomPasswordEncoder();
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
//		● setAllowCredentials : 내 서버가 응답을 할 때 json을 자바스크립트에서 처리할수 있게 할지를 설정
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
//		● addAllowedHeader : 허용할 헤더 목록
		config.addAllowedHeader("*");
//		● addAllowedMethod : 허용할 메서드(GET, PUT, 등) 목록
		config.addAllowedMethod("*");
//		● source.registerCorsConfiguration : 지정한 url에 config 적용
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		log.info("auth configure -----------");
		auth.userDetailsService(customUserDetailsService()).passwordEncoder(customPasswordEncoder());
	}

}

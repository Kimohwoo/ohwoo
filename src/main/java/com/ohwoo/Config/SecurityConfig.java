package com.ohwoo.Config;

import com.ohwoo.domain.*;
import com.ohwoo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserMapper userMapper;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// 한글 깨짐 발생시
//		CharacterEncodingFilter filter = new CharacterEncodingFilter();
//		filter.setEncoding("UTF-8");
//		filter.setForceEncoding(true);
//		http.addFilterBefore(filter, CsrfFilter.class);
		http.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // SessionCreationPolicy.STATELESS     - 스프링시큐리티가 생성하지도않고 기존것을 사용하지도 않음        ->JWT 같은토큰방식을 쓸때 사용하는 설정
				.and()
				.httpBasic().disable() //사용자 인증방법으로는 HTTP Basic Authentication을 사용 안한다.
				.addFilter(new JwtAuthenticationFilter(authenticationManager()))  // JwtAutienticationFilter : jwt를 사용해서 인증 처리
				.addFilter(new JwtAuthorizationFilter(authenticationManager(), userMapper)) // JwtAutiorizationFilter : jwt를 사용해서 인가 처리
				.authorizeRequests().antMatchers("/user/user-reg").permitAll()
//				.antMatchers("/user/login").permitAll()
				.antMatchers("/user/*")
				.access("hasAnyRole('USER','ADMIN')").antMatchers("/admin/*").access("hasRole('ADMIN')")
				.antMatchers("/board/list").permitAll().antMatchers("/board/*").access("hasAnyRole('USER','ADMIN')")
				.anyRequest().permitAll().and().rememberMe().tokenValiditySeconds(86400).key("myRememberMeKey")
				.userDetailsService(userDetailsService());
		http.formLogin().loginPage("/customLogin").loginProcessingUrl("/login").successHandler(loginSuccessHandler())
						.failureHandler(loginFailureHandler());
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
				.deleteCookies("remember-me", "JSESSION_ID");
	}

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}

	@Bean
	public AuthenticationFailureHandler loginFailureHandler() {
		return new CustomLoginFailureHandler();
	}

	@Bean
	public UserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public PasswordEncoder customPasswordEncoder() {
		// customPasswordEncoder를 생성하고 반환
		return new CustomPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		log.info("auth configure -----------");
		auth.userDetailsService(customUserDetailsService()).passwordEncoder(customPasswordEncoder());
	}

}

package com.ohwoo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ohwoo.domain.CustomLoginSuccessHandler;
import com.ohwoo.domain.CustomPasswordEncoder;
import com.ohwoo.domain.CustomUserDetailsService;

import lombok.extern.log4j.Log4j;

@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// 한글 깨짐 발생시
//		CharacterEncodingFilter filter = new CharacterEncodingFilter();
//		filter.setEncoding("UTF-8");
//		filter.setForceEncoding(true);
//		http.addFilterBefore(filter, CsrfFilter.class);
		http.csrf().disable().authorizeRequests().antMatchers("/user/addUser").permitAll().antMatchers("/user/*")
				.access("hasAnyRole('USER','ADMIN')").antMatchers("/admin/*").access("hasRole('ADMIN')")
				.antMatchers("/board/list").permitAll().antMatchers("/board/*").access("hasAnyRole('USER','ADMIN')")
				.anyRequest().permitAll().and().rememberMe().tokenValiditySeconds(86400).key("myRememberMeKey")
				.userDetailsService(userDetailsService());
//		http.formLogin().successHandler(loginSuccessHandler());
		http.formLogin().loginPage("/customLogin").loginProcessingUrl("/login").successHandler(loginSuccessHandler());
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
				.deleteCookies("remember-me", "JSESSION_ID");
	}

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
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
//		auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");
//		auth.userDetailsService(customUserDetailsService());
		auth.userDetailsService(customUserDetailsService()).passwordEncoder(customPasswordEncoder());
	}

}

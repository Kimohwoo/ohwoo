package com.ohwoo.Config;

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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ohwoo.Service.UserService;
import com.ohwoo.domain.CustomPasswordEncoder;
import com.ohwoo.domain.CustomUserDetailsService;
import com.ohwoo.domain.JwtAuthenticationFilter;
import com.ohwoo.domain.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;
	private UserService userService;

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.httpBasic().disable() // 사용자 인증방법으로는 HTTP Basic Authentication을 사용 안한다.
				.authorizeRequests().antMatchers("/board/*").hasRole("USER").antMatchers("/test").authenticated().and()
				.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider()),
						UsernamePasswordAuthenticationFilter.class); // JwtAutienticationFilter : jwt를 사용해서
//		http.formLogin().loginPage("/customLogin");
//		http.logout().logoutUrl("/logout").logoutSuccessUrl("/").deleteCookies("Authorization");
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public JwtTokenProvider jwtTokenProvider() {
		return new JwtTokenProvider(userDetailsService);
	}

	@Bean
	public UserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService(userService);
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

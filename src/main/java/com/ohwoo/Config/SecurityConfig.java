package com.ohwoo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		http.authorizeRequests().antMatchers("/user/*").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
				.antMatchers("/admin/*").access("hasRole('ROLE_ADMIN')").antMatchers("/board/list").permitAll()
				.antMatchers("/board/*").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')").anyRequest().permitAll();
		http.formLogin();
//		http.formLogin().loginPage("/customLogin").loginProcessingUrl("/login");
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
				.deleteCookies("remember-me", "JSESSION_ID");
	}

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
//	@Bean
//    public PasswordEncoder customPasswordEncoder() {
//        // customPasswordEncoder를 생성하고 반환
//        return new CustomPasswordEncoder();
//    }
	
	@Bean
	public BCryptPasswordEncoder encodepw() {
		log.warn("BCryptPasswordEncoder");
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		log.info("auth configure -----------");
		auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
	}

}

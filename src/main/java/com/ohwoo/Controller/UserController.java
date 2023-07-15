package com.ohwoo.Controller;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ohwoo.DTO.UserDTO;
import com.ohwoo.Service.UserService;
import com.ohwoo.domain.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/user/*")
@RequiredArgsConstructor
@Log4j
public class UserController {

	private final UserService userService;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	@GetMapping("{id}")
	public ModelAndView updateuser(@PathVariable("id")String username) {
		return new ModelAndView("/user/updateUser");
	}
	
	@GetMapping("/user-reg")
	public ModelAndView addUser() {
		ModelAndView mv = new ModelAndView("/user/addUser");
		return mv;
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<String> checkId(@PathVariable("id") String username) {
		log.info("idCheck : " + username);
		return ResponseEntity.status(HttpStatus.OK).body(userService.IdCheck(username));
	}

	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDTO user) {
		log.info("user email = " + user.getUsername());
		UserDTO cuser = userService.login(user.getUsername());
		if(user.getPassword() != null && passwordEncoder.matches(user.getPassword(), cuser.getPassword())) {
			HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", jwtTokenProvider.createToken(cuser.getUsername(), cuser.getAuthList()));

	        return ResponseEntity.status(HttpStatus.OK).headers(headers).body("Login successful");
		}
		log.info("Login failed");
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("failed");
	}

	@PostMapping("/user-reg")
	public UserDTO addUser1(@RequestBody UserDTO user) {
		log.info("회원가입" + user);
		if(userService.register(user)){
			return userService.login(user.getUsername());
		}
		return null;
	}
	
	@PostMapping("/user-check")
	public UserDTO getUser(Principal principal) {
		UserDTO user = null;
		if(principal.getName() != null) {
			log.info("principal : " + principal.getName()); 
			user = userService.login(principal.getName());
			return user;
		} else {
			return user;
		}
	}

	@PatchMapping("{id}")
	public ResponseEntity<String> modifyUser(@PathVariable("id") String username,@RequestBody UserDTO user) {
		log.info("수정 오는지 : " + username);
		if(userService.modify(user)) {
			return ResponseEntity.status(HttpStatus.OK).body("수정완료");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정 실패");
		}
		
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> remove(@PathVariable("id")String username, @RequestBody UserDTO user) {
		if(userService.delete(user)) {
			return ResponseEntity.status(HttpStatus.OK).body("회원 삭제");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
		}
	}

}

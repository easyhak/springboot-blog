package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;
	

	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		
		userService.회원수정(user);
		
		// 세션 등록
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	/*
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
		System.out.println("login 호출됨");
		User principal  = userService.login(user); // principal 접근주체
		
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	*/
}

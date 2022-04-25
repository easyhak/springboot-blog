package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service // 스프링이 component scan을 통해서 bean에 등록해준다.
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); // 원문
		String encPassword =  encoder.encode(rawPassword); // 해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
//		try {
//			userRepository.save(user);
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("UserService: 회원가입(): "+e.getMessage());
//		}
//		return -1;
		
	}
}

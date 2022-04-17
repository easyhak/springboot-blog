package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
	
	private static final String Tag = "HttpController Test: ";
	
	@GetMapping("/http/lombok")
	public Member getLombokTest() {
		Member m = Member.builder().username("sar").email("sar@naver").password("1234").id(123).build();
		Member m1 = new Member(1,"cos","1234","cos@naver.com");
		return m;
	}
	
	//인터넷 요청 방식은 get요청만 할 수 있음
	//http://localhost:8000/blog/http/get (select)
	@GetMapping("/http/get")
	public Member getTest(Member m) {
		System.out.println(Tag + "getter: " + m.getId());
		m.setId(100);
		System.out.println(Tag + "setter: "+ m.getId());
		String email = m.getUsername()+"@nate.com";
		m.setEmail(email);
		return m;
	}
	
	//http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post요청입니다" + m.getId() + m.getUsername() + m.getPassword() + m.getEmail();
	}
	
	//http://localhost:8080/http/put (update) 
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put요청입니다" + m.getId() + m.getUsername() + m.getPassword() + m.getEmail();
	}
	
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}

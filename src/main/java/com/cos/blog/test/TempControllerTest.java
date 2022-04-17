package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	// http://localhost:8000/blog/test/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("temphome()");
		//파일 기본 경로: src/main/resources/static
		// 리턴명 : /home.html
		// 풀경로: src/main/resources/static/home.html
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// prefix: /WEB-INF/views
		// suffix: jsp
		// 풀네임: /WEB-INF/views/test.jsp
		return "test";
	}
}

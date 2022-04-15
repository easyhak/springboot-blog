package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//스프링이 com.cos.blog 패키지 이하를 스캔해서 모든 파일을 new 하는 것은 아니라
//특정 어노태이션이 붙여있는 클래스 파일들을 new 해서 (IoC) 컨테이너에 관리해줌
 
@RestController
public class BlogControllerTest {
	//http://localhost:8080/test/hello
	@GetMapping("test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
}

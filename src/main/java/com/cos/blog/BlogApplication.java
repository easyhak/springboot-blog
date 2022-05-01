package com.cos.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	@Bean
	public PageableHandlerMethodArgumentResolverCustomizer customize() {
		return p->{
				p.setOneIndexedParameters(true); // page가 1부터 시작하게 한다 조심해서 써야할듯
		};
	}
}

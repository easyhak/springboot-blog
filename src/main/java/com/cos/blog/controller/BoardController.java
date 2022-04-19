package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
		@GetMapping({"","/"})
		public String index() {
		// prefix : WEB-INF/views/
		// sufix: .jsp
		return "index";
		}
}

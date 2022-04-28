package com.cos.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping({ "", "/" })
	public String index(ModelMap model,@PageableDefault(size = 3, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {
		int startPage = ((pageable.getPageNumber()-1) / 5) * 5 + 1;
        pageable.getPageSize();
        int endPage = startPage + 5 - 1  > pageable.getPageSize() ? pageable.getPageSize() : startPage + 5 - 1;
        model.addAttribute("startPageNo", startPage);
        model.addAttribute("endPageNo", endPage);
		model.addAttribute("boards",boardService.글목록(pageable));
		return "index";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.글상세보기(id));
		return "board/detail";
	}
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.글상세보기(id));
		return "board/updateForm";
	}
}

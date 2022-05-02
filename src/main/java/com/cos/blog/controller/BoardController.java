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
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping({ "", "/" })
	public String index(ModelMap model,@PageableDefault(size = 3, sort = "createDate", direction = Sort.Direction.DESC, page = 0) Pageable pageable,
			@RequestParam(name="page", required = false, defaultValue = "1")int nowPage ) {
		
		int totalCount = boardService.전체글수();
		int listCount = pageable.getPageSize();
		int pageCount = 5;
		int totalPage = totalCount / listCount;
		if (totalCount % listCount > 0)
			totalPage++;
		int paging;
		if ((nowPage)%pageCount != 0)
			paging = (nowPage)/pageCount;
		else
			paging = ((nowPage)/pageCount) - 1;
		int startPage = paging * pageCount + 1;
		int endPage = paging * pageCount + 5;
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		
		if(nowPage < 1) {
			return "redirect:/";
		}
		else if (nowPage > totalPage) {
			return "redirect:/?page="+totalPage;
		}
		// 잘못된 페이지로 가면 redirect 그리고 parameter page숫자 제대로 맞춰서 해보기 
        model.addAttribute("startPageNo", startPage);
        model.addAttribute("endPageNo", endPage);
        model.addAttribute("totalPageNo",totalPage);
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

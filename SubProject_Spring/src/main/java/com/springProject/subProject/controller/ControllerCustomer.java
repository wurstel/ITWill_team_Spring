package com.springProject.subProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springProject.subProject.svc.ServiceCustomer;
import com.springProject.subProject.vo.QnaVO;


@Controller
public class ControllerCustomer {
	
	@Autowired
	private ServiceCustomer service;
	
	// 글쓰기 폼
	@RequestMapping(value = "/customerCenter/write", method = RequestMethod.GET)
	public String write() {
		return "customerCenter/qna_board_write";
	}
	@RequestMapping(value = "/customerCenter/write", method = RequestMethod.POST)
	public String writePost(@ModelAttribute QnaVO qna, Model model) {
		int insertCount = service.writeBoard(qna);
		
		if (insertCount == 0) {
			model.addAttribute("msg", "글 등록 실패");
			return "main";
		}
		return "redirect:/customerCenter/qna_board_list";
	}
	@RequestMapping(value = "customerCenter", method = RequestMethod.GET)
	public String customer(@RequestParam(defaultValue = "1") int pageNum, Model model) {
		return "customerCenter/qna_board_list";
	}
}

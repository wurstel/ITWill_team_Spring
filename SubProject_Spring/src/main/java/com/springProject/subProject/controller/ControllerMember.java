package com.springProject.subProject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springProject.subProject.svc.ServiceMember;
import com.springProject.subProject.vo.MemberVO;

@Controller
public class ControllerMember {
//멤버에 관련된 회원가입,로그인작업

	@Autowired
	private ServiceMember service;
	
	//로그인 폼으로 이동
	@RequestMapping(value = "/login_form.me", method = RequestMethod.GET)
	public String loginForm() {
		return "member/mem_loginForm";
	}
	
	//로그인 로직
	@RequestMapping(value = "/login.me", method = RequestMethod.POST)
	public String login(HttpSession session,String mem_id,String mem_password) {
		String userId = service.login(mem_id,mem_password);
		System.out.println(userId);
		session.setAttribute("userId", userId);
		return "redirect:/";
	}
	
	//로그아웃
	@RequestMapping(value = "/logout.me", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	//회원가입 폼으로 이동
	@RequestMapping(value = "/join_form.me", method = RequestMethod.GET)
	public String joinForm() {
		return "member/mem_joinForm";
	}
	
	//아이디 중복확인 창 열기
	@RequestMapping(value = "/MemberCheckId.me", method = RequestMethod.GET)
	public String duplicatedId() {
		return "member/check_id";
	}
	
	//아이디 중복확인 로직
	@RequestMapping(value = "/CheckIdDuplicate.me", method = RequestMethod.GET)
	public String checkDuplicatedId(String mem_id) {
		System.out.println(mem_id);
		String isDuplicate = service.isDuplicate(mem_id);
		return "redirect:MemberCheckId.me?mem_id=" + mem_id + "&isDuplicate=" + isDuplicate;
	}
	
//	//회원가입
//	@RequestMapping(value = "/mem_join.me", method = RequestMethod.POST)
//	public String join(@ModelAttribute MemberVO memberVO, String mem_year,String mem_month,String mem_day,String mem_mailAdd,String domain,String mem_phoneF,String mem_phoneM,String mem_phoneL,String address,String add_detail, Model model) {
//		String mem_birth = mem_year + mem_month + mem_day;
//		String mem_email = mem_mailAdd + "@" +domain;
//		String mem_phoneNum = mem_phoneF + mem_phoneM + mem_phoneL;
//		String mem_address = address + add_detail;
//		
//		Integer insertCount = service.joinMember(memberVO,mem_birth,mem_email,mem_phoneNum,mem_address);
//		if(insertCount == 0) {			//회원가입 실패시
//			model.addAttribute("msg", "잘못된 접근입니다!");
//			return "/fail_back";
//		}
//		return "/";
//	}
	
	
	
}

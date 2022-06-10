package com.springProject.subProject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springProject.subProject.svc.ServiceMember;
import com.springProject.subProject.vo.BasketListVO;
import com.springProject.subProject.vo.MemberVO;
import com.springProject.subProject.vo.Order_checkVO;

@Controller
public class ControllerMember {
//멤버에 관련된 회원가입,로그인작업

	@Autowired
	private ServiceMember service;

	// 회원가입 폼으로 이동
	@RequestMapping(value = "/join_form.me", method = RequestMethod.GET)
	public String joinForm() {
		return "member/mem_joinForm";
	}

	// 아이디 중복확인 창 열기
	@RequestMapping(value = "/MemberCheckId.me", method = RequestMethod.GET)
	public String duplicatedId() {
		return "member/check_id";
	}

	// 아이디 중복확인 로직
	@RequestMapping(value = "/CheckIdDuplicate.me", method = RequestMethod.GET)
	public String checkDuplicatedId(String mem_id) {
		System.out.println(mem_id);
		String isDuplicate = service.isDuplicate(mem_id);
		return "redirect:MemberCheckId.me?mem_id=" + mem_id + "&isDuplicate=" + isDuplicate;
	}

	// 회원가입 비즈니스 로직
	@RequestMapping(value = "/mem_join.me", method = RequestMethod.POST)
	public String join(@ModelAttribute MemberVO memberVO, String mem_year, String mem_month, String mem_day,
			String mem_mailAdd, String domain, String mem_phoneF, String mem_phoneM, String mem_phoneL, String address,
			String add_detail, Model model) {
		String mem_birth = mem_year + mem_month + mem_day;
		String mem_email = mem_mailAdd + "@" + domain;
		String mem_phoneNum = mem_phoneF + mem_phoneM + mem_phoneL;
		String mem_address = address + add_detail;
		memberVO.setMem_birth(mem_birth);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_address(mem_address);
		memberVO.setMem_phoneNum(mem_phoneNum);

		int insertCount = service.joinMember(memberVO);
		if (insertCount == 0) { // 회원가입 실패시
			model.addAttribute("msg", "잘못된 접근입니다!");
			return "/fail_back";
		}
		return "redirect:/";
	}

	// 로그인 폼으로 이동
	@RequestMapping(value = "/login_form.me", method = RequestMethod.GET)
	public String loginForm() {
		return "member/mem_loginForm";
	}

	// 로그인 비즈니스 로직
	@RequestMapping(value = "/login.me", method = RequestMethod.POST)
	public String login(HttpSession session, String mem_id, String mem_password) {
		String userId = service.login(mem_id, mem_password);
		System.out.println(userId);
		session.setAttribute("userId", userId);
		return "redirect:/";
	}

	// 로그아웃
	@RequestMapping(value = "/logout.me", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	// 마이페이지
	@RequestMapping(value = "/mypage.me", method = RequestMethod.GET)
	public String getMemberInfo(HttpSession session, Model model) {
		String id = (String) session.getAttribute("userId");
		if (id == null) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			return "member/fail_back";
		}
		MemberVO memberVO = service.getMyPage(id);
		System.out.println("아이디 : " + memberVO.getMem_id());
		model.addAttribute("memberVO", memberVO);
		return "member/mem_mypage";
	}

	// 회원정보 변경 폼 이동
	@RequestMapping(value = "/memInfoEdit.me", method = RequestMethod.GET)
	public String infoEdit(HttpSession session, Model model) {
		String id = (String) session.getAttribute("userId");
		MemberVO memberVO = service.getMyPage(id);
		System.out.println("아이디 : " + memberVO.getMem_id());
		model.addAttribute("memberVO", memberVO);
		return "member/mem_infoEdit";
	}

	// 회원 정보 변경 비즈니스 로직
	@RequestMapping(value = "/memInfoEdit.me", method = RequestMethod.POST)
	public String infoEditPost(HttpSession session, @ModelAttribute MemberVO memberVO, String mem_mailAdd,
			String domain, String mem_phoneNum, String mem_postcode, String address, String add_detail, Model model) {
		String id = (String) session.getAttribute("userId");
		String mem_email = mem_mailAdd + "@" + domain;
		String mem_address = address + add_detail;
		memberVO.setMem_id(id);
		System.out.println("아이디 : " + id);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_phoneNum(mem_phoneNum);
		memberVO.setMem_postcode(mem_postcode);
		memberVO.setMem_address(mem_address);

		int updateCount = service.updateMember(memberVO);
		if (updateCount == 0) {
			model.addAttribute("msg", "회원정보변경 실패");
			return "fail_back";
		}
		model.addAttribute("memberVO", memberVO);

		return "redirect:/mypage.me";
	}
	
	//주문조회 
	@RequestMapping(value = "/inquiry.me", method = RequestMethod.GET)
	public String inquiry(HttpSession session,Model model) {
		String mem_id = (String)session.getAttribute("userId");
		String isInquiry = service.isInquiry(mem_id);
		
		if(isInquiry == null) {			//주문내역이 있는경우
			List<Order_checkVO> list = service.loadInquiry(mem_id);		//주문내역 불러오기
			model.addAttribute("list", list);
		}else {							//주문내역이 없는경우
			model.addAttribute("comment", isInquiry);
		}
		
		return "member/mem_orderInquiry";
	}
	
	//장바구니 목록 불러오기
	@RequestMapping(value = "/basket.me", method = RequestMethod.GET)
	public String basket(HttpSession session, Model model) {
	    String id = (String) session.getAttribute("userId");
	    ArrayList<BasketListVO> basketlist = service.getBasketList(id);
	
	    model.addAttribute("basketlist", basketlist);
	
	    return "member/mem_basket";
	}
	
	//장바구니 수량 변경
	@RequestMapping(value = "/basketUpdate.me", method = RequestMethod.GET)
	public String basketUpdate(HttpSession session,BasketListVO basketListVO, Model model) {
		System.out.println(basketListVO);
		int updateCount = service.updateBasket(basketListVO);
		
		return "redirect:basket.me";
	}
	
	// 마이페이지 - 장바구니 삭제
    @RequestMapping(value = "/basketDelete.me", method = RequestMethod.GET)
    public String deleteBasket(String bk_mem_id, String bk_order_num, String bk_pd_code) {
       System.out.println("DELETE : " + bk_mem_id + bk_order_num + bk_pd_code);
       service.deleteBasket(bk_mem_id, bk_order_num, bk_pd_code);
       return "redirect:/basket.me";
    }
	
	
	
	
	
	
	
	
}

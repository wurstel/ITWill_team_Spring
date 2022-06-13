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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springProject.subProject.svc.ServiceMember;
import com.springProject.subProject.vo.BasketListVO;
import com.springProject.subProject.vo.MemberVO;
import com.springProject.subProject.vo.Order_checkVO;
import com.springProject.subProject.vo.member_authVO;

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
	// 회원가입 인증로직
	@RequestMapping(value = "/mem_joinSuccess.me", method = RequestMethod.GET) // POST로 변경 URL에 나옴
	public String joinSuccess(@ModelAttribute member_authVO authVO) {

		String auth = service.selectAuthInfo(authVO); // 기존 인증코드 조회
		if(auth.equals(null)) {
			service.insertAuthInfo(authVO);
		}
		return "member/mem_joinSuccess";
	}
	// 회원가입 인증메일 전송 로직
	@RequestMapping(value = "/mem_sendmail.me", method = RequestMethod.GET)
	public ModelAndView sendEmail(@ModelAttribute MemberVO memberVO,String mem_email) throws Exception {

		ModelAndView mv = new ModelAndView();
		
		String email = "email";
		String mem_id = memberVO.getMem_id();
		String addr = memberVO.getMem_email(); // 받는사람
		String subject = "회원 가입 인증 메일입니다.";
		String body = "인증하려면 아래 링크를 클릭하세요"
				+"<a href='http://localhost:8080/subProject/member_authentication.me?mem_id=" + mem_id + "'>인증하기</a>";
		service.sendEmail(email, addr, subject, body);
		System.out.println(mem_id);
		mv.setViewName("redirect:/");
		return mv; 
	}
		
	// 회원가입 비즈니스 로직
	@RequestMapping(value = "/mem_join.me", method = RequestMethod.POST)
	public String join(@ModelAttribute MemberVO memberVO,@ModelAttribute member_authVO authVO,@RequestParam String mem_id,@RequestParam String mem_auth_code, String mem_year, String mem_month, String mem_day,
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
		service.insertAuthInfo(authVO);
		if (insertCount == 0) { // 회원가입 실패시
			model.addAttribute("msg", "잘못된 접근입니다!");
			return "/fail_back";
		}
		return "redirect:/mem_joinSuccess.me?mem_id="+mem_id+"&mem_email="+mem_email;
		// return "redirect:/send_authentication_code.jsp?id=" + id + "&email=" + email";
		// 회원 가입 성공 시 인증 메일 발송을 위한 send_authentication_code.jsp 페이지로 이동 (임의의 주소임)
		// => 파라미터로 아이디(id)와 이메일주소(email) 전송	
	}
	// 회원가입 인증클릭로직
	@RequestMapping(value = "/member_authentication.me", method = RequestMethod.GET)
	public String authentication(@ModelAttribute member_authVO authVO) {
		String result = service.selectAuthInfo(authVO);
		if(!result.equals(null)) {
			service.deleteAuth(authVO);
			service.updateAuth(authVO);
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
		// member_authentication.jsp 로 보내서 인증여부 판별
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

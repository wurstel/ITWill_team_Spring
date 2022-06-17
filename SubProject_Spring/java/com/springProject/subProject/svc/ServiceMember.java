package com.springProject.subProject.svc;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.springProject.subProject.mapper.MemberMapper;
import com.springProject.subProject.vo.BasketListVO;
import com.springProject.subProject.vo.MemberVO;
import com.springProject.subProject.vo.Order_checkVO;
import com.springProject.subProject.vo.member_authVO;


@Service
public class ServiceMember {
	@Autowired
	 private JavaMailSender mailSender;
	
	
	@Autowired
	private MemberMapper mapper;


	private SimpleMailMessage preConfiguredMessage;
	
	// 이메일 보내
	@Async
	public void sendEmail(String to, String subject, String body) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
	    	   MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	    	   messageHelper.setSubject(subject);
	    	   messageHelper.setTo(to);
	    	   messageHelper.setFrom("caras134679@gmail.com", "홍길동");
	    	   messageHelper.setText(body,true);
	    	   mailSender.send(message);
	    	  }catch(Exception e){
	    		  e.printStackTrace();
	    	  }


	}
	
 	@Async
    public void sendPreConfiguredMail(String message) {
            SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
            mailMessage.setText(message);
            mailSender.send(mailMessage);
    }


	
	
	
	
	public String isDuplicate(String id) {
		return mapper.isDuplicate(id);
	}
	
	// 로그인
	public String login(String mem_id, String mem_password) {
		return mapper.searchUser(mem_id,mem_password);
	}

	// 회원가입
	public int joinMember(@ModelAttribute MemberVO memberVO) {
	System.out.println(memberVO);
	
		return mapper.insertMember(memberVO);
	}
	// 회원인증조회
	public String selectAuthInfo(@ModelAttribute member_authVO authVO) {
		return mapper.authInfo(authVO);
	}
	public void insertAuthInfo(@ModelAttribute member_authVO authVO) {
		mapper.insertAutoInfo(authVO);
	}
	
	public void updateAuthInfo(@ModelAttribute member_authVO authVO) {
		mapper.updateAuthInfo(authVO);
	}
	// 인증 후 코드 삭제
	public void deleteAuth(@ModelAttribute member_authVO authVO) {
		mapper.deleteAuth(authVO);
	}
	// 인증 성공으로 변경
	public void updateAuth(@ModelAttribute member_authVO authVO) {
		mapper.updateAuth(authVO);
	}

	// 마이 페이지
	public MemberVO getMyPage(String id) {
		return mapper.selectMyPage(id);
	}

	// 회원정보 변경 폼 이동 -> 회원정보 가져오기
	public MemberVO getMemberDetail(String id) {
		
		return mapper.selectMemberDetail(id);
	}
	
	// 회원정보 수정 
	public int updateMember(@ModelAttribute MemberVO memberVO) {
//		memberVO.setMem_email(memberVO.getMem_email());
//		memberVO.setMem_phoneNum(memberVO.getMem_phoneNum());
//		memberVO.setMem_postcode(memberVO.getMem_postcode());
//		memberVO.setMem_address(memberVO.getMem_address());
		System.out.println("수정된 정보 : " + memberVO);
		
		return mapper.updateMember(memberVO);
	}
	
	// 장바구니 불러오기
	public ArrayList<BasketListVO> getBasketList(String id) {
	      return mapper.selectMyBasket(id);
	}

	//장바구니 수량 변경
	public int updateBasket(BasketListVO basketListVO) {
		return mapper.updateBasket(basketListVO);
	}
	
	//장바구니 물품 삭재
	public void deleteBasket(String bk_mem_id, String bk_order_num, String bk_pd_code) {
		mapper.deleteMyBasket(bk_mem_id, bk_order_num, bk_pd_code);
	}
	
	//주문내역 확인
	public String isInquiry(String mem_id) {
		return mapper.isInquiry(mem_id);
	}

	//주문내역 불러오기
	public List<Order_checkVO> loadInquiry(String mem_id) {
		return mapper.loadInquiry(mem_id);
	}


	


}

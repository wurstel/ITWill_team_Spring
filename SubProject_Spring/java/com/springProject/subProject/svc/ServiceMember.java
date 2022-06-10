package com.springProject.subProject.svc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.springProject.subProject.mapper.MemberMapper;
import com.springProject.subProject.vo.BasketListVO;
import com.springProject.subProject.vo.MemberVO;
import com.springProject.subProject.vo.Order_checkVO;


@Service
public class ServiceMember {

	@Autowired
	private MemberMapper mapper;

	public String isDuplicate(String id) {
		return mapper.isDuplicate(id);
	}
	
	// 로그인
	public String login(String mem_id, String mem_password) {
		return mapper.searchUser(mem_id,mem_password);
	}

	

	public int joinMember(@ModelAttribute MemberVO memberVO) {
	System.out.println(memberVO);
	
	
		return mapper.insertMember(memberVO);
	}

	// 마이 페이지
	public MemberVO getMyPage(String id) {
		// TODO Auto-generated method stub
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

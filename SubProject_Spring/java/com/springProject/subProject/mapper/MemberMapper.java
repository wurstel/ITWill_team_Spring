package com.springProject.subProject.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.springProject.subProject.vo.BasketListVO;
import com.springProject.subProject.vo.MemberVO;
import com.springProject.subProject.vo.Order_checkVO;
import com.springProject.subProject.vo.member_authVO;

public interface MemberMapper {

	String isDuplicate(String id);

	// 로그인
	String searchUser(@Param("mem_id") String mem_id,@Param("mem_password") String mem_password);

	// 회원가입
	int insertMember(@ModelAttribute MemberVO memberVO);

	// 회원인증
	String authInfo(@ModelAttribute member_authVO authvo);
	
	void insertAutoInfo(@ModelAttribute member_authVO authvo);
	
	void updateAuthInfo(@ModelAttribute member_authVO authvo);
	
	// 인증 후 삭제
	void deleteAuth(member_authVO authVO);
	
	// 인증 성공으로 변경
	void updateAuth(member_authVO authVO);
	
	MemberVO selectMyPage(String id);

	MemberVO selectMemberDetail(String id);
	
	// 회원 정보 수정
	int updateMember(@ModelAttribute MemberVO memberVO);

	// 장바구니 불러오기
	ArrayList<BasketListVO> selectMyBasket(String id);

	//장바구니 수량 변경
	int updateBasket(BasketListVO basketListVO);

	// 장바구니 삭제
   void deleteMyBasket(@Param("bk_mem_id") String bk_mem_id,@Param("bk_ord	er_num") String bk_order_num,@Param("bk_pd_code") String bk_pd_code);

   //주문내역 확인
   String isInquiry(String mem_id);

   //주문내역 불러오기
   List<Order_checkVO> loadInquiry(String mem_id);









}

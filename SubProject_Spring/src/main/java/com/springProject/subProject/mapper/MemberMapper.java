package com.springProject.subProject.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.springProject.subProject.vo.MemberVO;

public interface MemberMapper {

	String isDuplicate(String id);

//	Integer insertMember(@ModelAttribute MemberVO memberVO, String mem_birth, String mem_email, String mem_phoneNum, String mem_address);

	String searchUser(@Param("mem_id") String mem_id,@Param("mem_password") String mem_password);

	
	int insertMember(@ModelAttribute MemberVO memberVO);

	MemberVO selectMyPage(String id);

	MemberVO selectMemberDetail(String id);
	
	// 회원 정보 수정
	int updateMember(@ModelAttribute MemberVO memberVO);



}

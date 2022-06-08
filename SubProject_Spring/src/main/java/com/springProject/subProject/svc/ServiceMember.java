package com.springProject.subProject.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.springProject.subProject.mapper.MemberMapper;
import com.springProject.subProject.vo.MemberVO;


@Service
public class ServiceMember {

	@Autowired
	private MemberMapper mapper;

	public String isDuplicate(String id) {
		return mapper.isDuplicate(id);
	}

//	public Integer joinMember(@ModelAttribute MemberVO memberVO, String mem_birth, String mem_email, String mem_phoneNum, String mem_address) {
//		System.out.println(memberVO);
//		System.out.println(mem_birth);
//		System.out.println(mem_email);
//		System.out.println(mem_phoneNum);
//		System.out.println(mem_address);
//		return mapper.insertMember(memberVO,mem_birth,mem_email,mem_phoneNum,mem_address);
//	}

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

	


}

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



}

package com.springProject.subProject.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.springProject.subProject.mapper.PaymentMapper;
import com.springProject.subProject.vo.payInfoVO;

@Service
public class ServicePayment {

	@Autowired
	PaymentMapper mapper;
	
	
	public payInfoVO getPayInfo(String userId, String pd_code) {
		return mapper.getPayInfo(userId,pd_code);
	}


	public int insertOrderPad(Model model) {
		return mapper.insertOrderPad(model);
	}

}

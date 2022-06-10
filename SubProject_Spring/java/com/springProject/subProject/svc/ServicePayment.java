package com.springProject.subProject.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.springProject.subProject.mapper.PaymentMapper;
import com.springProject.subProject.vo.Order_padVO;
import com.springProject.subProject.vo.PayInfoVO;

@Service
public class ServicePayment {

	@Autowired
	PaymentMapper mapper;
	
	
	public PayInfoVO getPayInfo(Order_padVO order_padVO) {
		return mapper.getPayInfo(order_padVO);
	}


	public int insertOrderPad(Order_padVO order_padVO) {
		return mapper.insertOrderPad(order_padVO);
	}

}

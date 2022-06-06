package com.springProject.subProject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springProject.subProject.svc.ServicePayment;
import com.springProject.subProject.vo.payInfoVO;

@Controller
public class ControllerPayment {
//결제에 관련된 이동
	@Autowired
	ServicePayment service;
	
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String selectPay(String userId,String pd_code,Model model) {
		
		int insertCount = service.insertOrderPad(model);
		
		
		payInfoVO payInfoVO = service.getPayInfo(userId,pd_code);
		model.addAttribute("payInfoVO", payInfoVO);
		return "payment/payment";
	}
	
	@RequestMapping(value = "/paymentResult", method = RequestMethod.POST)
	public String payResult() {
		return "payment/payment_result";
	}
}

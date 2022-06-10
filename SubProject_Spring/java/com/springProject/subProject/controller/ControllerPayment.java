package com.springProject.subProject.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springProject.subProject.svc.ServicePayment;
import com.springProject.subProject.vo.Order_padVO;
import com.springProject.subProject.vo.PayInfoVO;

@Controller
public class ControllerPayment {
//결제에 관련된 이동
	@Autowired
	ServicePayment service;
	
	@RequestMapping(value = "/Payment.pm", method = RequestMethod.POST)
	public String selectPay(@ModelAttribute Order_padVO order_padVO,String address,String address_detail ,HttpSession session,Model model) {
		String order_address = address + address_detail;
		String userId = (String)session.getAttribute("userId");
		order_padVO.setOrder_address(order_address);
		order_padVO.setOrder_mem_id(userId);
		System.out.println(order_padVO);
		System.out.println(userId);
		int insertCount = service.insertOrderPad(order_padVO);
		
		
		PayInfoVO payInfoVO = service.getPayInfo(order_padVO);
		System.out.println(payInfoVO);
		model.addAttribute("payInfoVO", payInfoVO);
		return "payment/payment";
	}
	
	@RequestMapping(value = "/paymentResult", method = RequestMethod.GET)
	public String payResult() {
		return "payment/payment_result";
	}
}

package com.springProject.subProject.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springProject.subProject.svc.ServicePayment;
import com.springProject.subProject.vo.BasketListVO;
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
		int insertCount = service.insertOrderPad(order_padVO);		//주문내역에 등록
		
		
		PayInfoVO payInfoVO = service.getPayInfo(order_padVO);		//등록된 주문내역으로 결제정보 가져오기
		System.out.println(payInfoVO);
		model.addAttribute("payInfoVO", payInfoVO);
		return "payment/payment";
	}
	
	@RequestMapping(value = "/PaymentStore.pm", method = RequestMethod.POST)
	public String selectPayStore(HttpSession session,Model model) {
		ArrayList<BasketListVO> list = (ArrayList<BasketListVO>)session.getAttribute("basketlist");
		ArrayList<Order_padVO> orderPadList = new ArrayList<Order_padVO>();
	
		for(BasketListVO basketListVO : list) {
			service.insertBasketOrderPad(basketListVO);
		}		// 장바구니 상품을 주문표에 넣기
		
		for(BasketListVO basketListVO : list) {
			Order_padVO order_padVO = new Order_padVO(); 
			order_padVO = service.getOrderPad(basketListVO);
			System.out.println(order_padVO);
			orderPadList.add(order_padVO);
		}//주문표 가져오기 
		System.out.println(orderPadList);
		double amount = 0;
		for(BasketListVO basketListVO : list) {
			System.out.println(basketListVO.getTotalprice());
			amount += Double.parseDouble(basketListVO.getTotalprice()); 
		}
		System.out.println(amount);
		//결제정보세팅
		PayInfoVO payInfoVO = new PayInfoVO();
		payInfoVO.setPd_name(list.get(0).getPd_name()+" 외 " +list.size());
		payInfoVO.setAmount(Double.toString(amount));
		payInfoVO.setMem_name(list.get(0).getBk_mem_id());
		payInfoVO.setMem_phoneNum(orderPadList.get(0).getOrder_phoneNum()); 
		payInfoVO.setOrder_address(orderPadList.get(0).getOrder_address());
		payInfoVO.setOrder_postcode(orderPadList.get(0).getOrder_postcode());
		
		model.addAttribute("payInfoVO", payInfoVO);
		
		return "payment/payment";
	}
	
	@RequestMapping(value = "/paymentResult", method = RequestMethod.GET)
	public String payResult() {
		return "payment/payment_result";
	}
}

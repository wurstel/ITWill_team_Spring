package com.springProject.subProject.mapper;


import com.springProject.subProject.vo.Order_padVO;
import com.springProject.subProject.vo.PayInfoVO;

public interface PaymentMapper {

	int insertOrderPad(Order_padVO order_padVO);

	PayInfoVO getPayInfo(Order_padVO order_padVO);

}

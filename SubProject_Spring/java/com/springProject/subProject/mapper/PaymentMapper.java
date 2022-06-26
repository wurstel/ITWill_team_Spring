package com.springProject.subProject.mapper;


import com.springProject.subProject.vo.BasketListVO;
import com.springProject.subProject.vo.MemberVO;
import com.springProject.subProject.vo.Order_padVO;
import com.springProject.subProject.vo.PayInfoVO;
import com.springProject.subProject.vo.ProductVO;

public interface PaymentMapper {

	int insertOrderPad(Order_padVO order_padVO);

	PayInfoVO getPayInfo(Order_padVO order_padVO);

	void insertBasketOrderPad(BasketListVO basketListVO);

	Order_padVO getOrderPad(BasketListVO basketListVO);

	void updateStatus(BasketListVO basketListVO);

	void deleteOrderCheck(BasketListVO basketListVO);

	void updateSubStatus(String mem_id);

	MemberVO getMemInfo(String sId);

	ProductVO getProduct(BasketListVO listVO);
	
	MemberVO addressCofirm(String sId);
	
}

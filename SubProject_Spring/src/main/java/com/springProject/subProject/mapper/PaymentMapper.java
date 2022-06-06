package com.springProject.subProject.mapper;

import org.springframework.ui.Model;

import com.springProject.subProject.vo.payInfoVO;

public interface PaymentMapper {

	payInfoVO getPayInfo(String userId, String pd_code);

	int insertOrderPad(Model model);

}

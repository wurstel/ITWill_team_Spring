package com.springProject.subProject.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springProject.subProject.mapper.MapperCustomer;
import com.springProject.subProject.vo.QnaVO;


@Service
public class ServiceCustomer {

	@Autowired
	private MapperCustomer mapper;
	
	public int writeBoard(QnaVO qna) {
		
		Integer num = mapper.selectMaxNum();
		return 0;
	}

	

}

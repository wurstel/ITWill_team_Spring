package com.springProject.subProject.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springProject.subProject.mapper.MapperStore;
import com.springProject.subProject.vo.PageInfo;
import com.springProject.subProject.vo.ProductVO;
import com.springProject.subProject.vo.ReviewVO;

@Service
public class ServiceStore {
	@Autowired MapperStore mapper;
	
	public int getProductListCount() {
		return mapper.selectProductListCount();
	}
	
	public List<ProductVO> getProductList(PageInfo pageInfo, String select) {
		return mapper.selectProductList(pageInfo, select);
	}

	public ProductVO getProductDetail(String pd_code) {
		return mapper.selectProductDetail(pd_code);
	}

	public List<ReviewVO> getProductReviewList(String pd_code, String choice) {
		return mapper.selectProductReviewList(pd_code, choice);
	}

	public int getReviewCount(String pd_code) {
		return mapper.selectReviewCount(pd_code);
	}

	public int getReviewAvg(String pd_code) {
		return mapper.selectReviewAvg(pd_code);
	}


}

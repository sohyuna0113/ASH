package com.ashmall.service;

import java.util.List;

import com.ashmall.domain.ReviewVO;
import com.ashmall.util.Criteria;

public interface ReviewService {

	// 상품후기 총 갯수
	public int countReview(int pd_num) throws Exception;

	// 상품후기 쓰기
	public void writeReview(ReviewVO vo, String mb_id) throws Exception;
	
	// 상품후기 리스트(페이징포함)
	public List<ReviewVO> listReview(int pd_num, Criteria cri) throws Exception;
	
	// 상품후기 삭제
	public void deleteReview(int rv_num) throws Exception;
	
	// 상품후기 수정 
	public void modifyReview(ReviewVO vo) throws Exception;
}

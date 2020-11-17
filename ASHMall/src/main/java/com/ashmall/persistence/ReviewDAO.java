package com.ashmall.persistence;

import java.util.List;
import java.util.Map;

import com.ashmall.domain.ReviewVO;

public interface ReviewDAO {

	
	// 상품후기 총개수
	public int countReview(int pd_num) throws Exception;
	
	// 상품후기 쓰기
	public void writeReview(ReviewVO vo) throws Exception;
	
	// 상품후기 리스트(페이징포함)
	public List<ReviewVO> listReview(Map<String, Object> map) throws Exception;
	
	// 상품후기 삭제
	public void deleteReview(int rv_num) throws Exception;
	
	// 상품후기 수정
	public void modifyReview(ReviewVO vo) throws Exception;
}

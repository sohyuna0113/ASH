package com.ashmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashmall.domain.ReviewVO;
import com.ashmall.persistence.ReviewDAO;
import com.ashmall.util.Criteria;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDAO dao;
	
	// 상품후기 총갯수 
	@Override
	public int countReview(int pd_num) throws Exception {
		return dao.countReview(pd_num);
	}

	// 상품후기 쓰기 
	@Override
	public void writeReview(ReviewVO vo, String mb_id) throws Exception {
		vo.setMb_id(mb_id);
		dao.writeReview(vo);
	}

	// 상품후기 리스트(페이징포함) 
	@Override
	public List<ReviewVO> listReview(int pd_num, Criteria cri) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		map.put("pd_num", pd_num);
		map.put("cri", cri);
		
		return dao.listReview(map);
	}

	// 상품후기 삭제 
	@Override
	public void deleteReview(int rv_num) throws Exception {
		dao.deleteReview(rv_num);
	}

	// 상품후기 수정 
	@Override
	public void modifyReview(ReviewVO vo) throws Exception {
		dao.modifyReview(vo);

	}

}

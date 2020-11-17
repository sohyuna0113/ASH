package com.ashmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ashmall.domain.ReviewVO;
import com.ashmall.dto.MemberDTO;
import com.ashmall.service.ReviewService;
import com.ashmall.util.Criteria;
import com.ashmall.util.PageMaker;

@Controller
@RequestMapping(value="/review/*")
public class ReviewController {
	
	@Inject
	private ReviewService service;
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	/* 상품 후기 쓰기 */ 
	@ResponseBody
	@RequestMapping(value="write", method=RequestMethod.POST)
	public void write(ReviewVO vo, HttpSession session) throws Exception {
		
		logger.info("=====writePOST() execute...");
		logger.info("=====vo: "+ vo.toString());
		
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		
		service.writeReview(vo, dto.getMb_id());
	}
	
	/* 상품 후기 수정 */
	@ResponseBody
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public ResponseEntity<String> modify(ReviewVO vo){	
		
		logger.info("=====modify() execute");
		logger.info("=====vo: "+ vo.toString());
		
		ResponseEntity<String> entity = null;
		
		try { 
			service.modifyReview(vo);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		
		} catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;
	}

	/* 상품 후기 삭제 */
	@ResponseBody
	@RequestMapping(value="{rv_num}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteReview(@PathVariable("rv_num") int rv_num){
		
		logger.info("=====delete() execute");
		
		ResponseEntity<String> entity = null;
		
		try {
			service.deleteReview(rv_num);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;
	}
	
	/* 상품 후기 리스트 (페이지 포함) */ 
	@RequestMapping(value = "{pd_num}/{page}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listReview(
							@PathVariable("pd_num") Integer pd_num,
							@PathVariable("page") Integer page) {
		
		logger.info("=====listReview() execute");
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			
			Map<String, Object> map = new HashMap<String, Object>();
			List<ReviewVO> list = service.listReview(pd_num, cri);
			
			// 후기 목록(페이지 포함)추가 
			map.put("list", list);
			// 총 후기 개수 설정 
			int replyCount = service.countReview(pd_num);
			
			pageMaker.setTotalCount(replyCount);
			// 하단 페이지 작업추가 
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);			
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
			
		}
	
		return entity;
	}
	
}
	
	
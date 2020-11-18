package com.ashmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ashmall.domain.MemberVO;

import com.ashmall.domain.OrderListVO;
import com.ashmall.domain.OrderVO;

import com.ashmall.service.AdminService;

import com.ashmall.util.PageMaker;
import com.ashmall.util.SearchCriteria;

@Controller
@RequestMapping("/admin/member/*")

public class AdMbController {

	@Autowired
	private AdminService service;
	
	private static final Logger logger = LoggerFactory.getLogger(AdMbController.class);
	
	/* admin의 유저관리기능 */
	
	/* 관리자 유저목록기능(GET) */
	@RequestMapping(value = "/userList", method=RequestMethod.GET)
	public void userList(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		
		logger.info(cri.toString());
		
		model.addAttribute("userList", service.userlistSearch(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.userlistSearchCount(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	/* 관리자 유저삭제기능(GET) */
	@RequestMapping(value="/userDelete", method=RequestMethod.GET)
	public String userDelete(@RequestParam("mb_id") String mb_id, @ModelAttribute("cri") SearchCriteria cri) throws Exception {
		
		logger.info(mb_id);
		service.userDelete(mb_id);
		
		logger.info(mb_id + "=====userDelete() executed");
		
	    return "redirect:/admin/member/userList?page=" + cri.getPage() 
		+ "&perPageNum=" + cri.getPerPageNum()
		+ "&searchType=" + cri.getSearchType()
		+ "&keyword=" + cri.getKeyword();	
	}
	
	/* 관리자 회원정보보기(GET) */
	@RequestMapping(value="/userInfo", method=RequestMethod.GET)
	public void userInfo(@ModelAttribute("cri") SearchCriteria cri, MemberVO vo, Model model) throws Exception {
		
		logger.info("=====userInfo() executed");
		MemberVO vo2 = service.userInfo(vo);
		model.addAttribute(vo2);
		
		/*
		MemberDTO dto = new MemberDTO();
		dto.setMb_name(vo.getMb_name());
		dto.setMb_id(vo.getMb_id());
		
		service.userModify(vo);
		*/
		
	}
	
	/* 관리자 유저정보수정(GET) */
	@RequestMapping(value="/userModify", method=RequestMethod.GET)
	public void userModifyGET(@ModelAttribute("cri") SearchCriteria cri, MemberVO vo, Model model, HttpServletRequest request) throws Exception {
		
		model.addAttribute(service.userInfo(vo));
	}
	
	/* 관리자 유저정보수정(POST) */
	public String userModifyPOST(MemberVO vo, @ModelAttribute("cri") SearchCriteria cri, HttpServletRequest request) throws Exception {
		
		logger.info(vo.toString());
		service.userModify(vo);
		
		return "redirect:/admin/member/userInfo?page=" + cri.getPage() 
		+ "&perPageNum=" + cri.getPerPageNum()
		+ "&searchType=" + cri.getSearchType()
		+ "&keyword=" + cri.getKeyword()
		+ "&mb_id=" + vo.getMb_id();
	}
	
	/* 
	 * 주문 조회 작업(GET)
	 */
	// 주문 목록 
	@RequestMapping(value="/userOrder", method=RequestMethod.GET)
	public void orderList(@ModelAttribute("cri") SearchCriteria cri,
						  @ModelAttribute("categ_now") String categ_now,
						  Model model, HttpSession session, OrderVO vo) throws Exception {
		
		logger.info("=====listGET() execute");
		logger.info("=====cri :" + cri.toString());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categ_now", categ_now);
		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
		
		List<OrderVO> orderList = service.orderList();
		model.addAttribute("orderList", orderList);
		
	}
	/*
	// 주문 상세 목록
	@RequestMapping(value="/member/readOrder", method=RequestMethod.GET)
	public void orderList(@RequestParam("i") int od_num,
							OrderVO vo, Model model) throws Exception {
	
		logger.info("=====listGET() execute"); 
		
		vo.setOd_num(od_num);
		List<OrderListVO> readOrder = service.readOrder(od_num);
		
		model.addAttribute("readOrder", readOrder);
		
	}
	*/
	// 주문 상세 목록 (AJAX) 
	@ResponseBody
	@RequestMapping(value="/readOrder/{od_num}", method=RequestMethod.GET)
	public ResponseEntity<List<OrderListVO>> readOrder(@PathVariable("od_num") int od_num) throws Exception {
	
		
		logger.info("data" + service.readOrder(od_num));
		
		ResponseEntity<List<OrderListVO>> entity = null; 
		
		
		
		try {
			entity = new ResponseEntity<List<OrderListVO>>(service.readOrder(od_num), HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<List<OrderListVO>>(HttpStatus.BAD_REQUEST);
		}
		
		logger.info("=====listGET() execute"); 
		
		return entity;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ResponseEntity<String> deleteOrder(int od_num) throws Exception {
		
		logger.info("=====delete() execute"); 
		
		ResponseEntity<String> entity = null;
		
		try {
			service.deleteOrder(od_num);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}
	
		return entity;
	}

}









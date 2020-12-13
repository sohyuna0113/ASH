package com.ashmall.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ashmall.domain.BoardVO;
import com.ashmall.dto.MemberDTO;
import com.ashmall.service.BoardService;

@Controller
@RequestMapping(value="/board/*")
public class BoardController {

	@Inject
	private BoardService service;
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	/* 게시판 리스트 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void getList() {
		
	}
	
	
	/* 게시판 글쓰기 */
	public void regist(BoardVO vo, HttpSession session) throws Exception {
		
		logger.info("=====registPOST() exectue...");
		logger.info("=====vo: "+ vo.toString());

		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		
		service.regist(vo);
	}
	
	

		
}

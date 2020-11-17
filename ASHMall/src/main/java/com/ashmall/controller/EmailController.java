package com.ashmall.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ashmall.dto.EmailDTO;
import com.ashmall.service.EmailService;

@Controller
@RequestMapping("/email/*")
public class EmailController {

	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	@Inject
	private EmailService emailservce;
	
	/* ajax방식 이메일 인증코드 보내기 */
	/* @ModelAttribute EmailDTO dto : dto 파라미터의 데이터를 Model추가로 jsp파일안에서 사용하려는 목적 */
	@ResponseBody
	@RequestMapping("send")
	public ResponseEntity<String> send(@ModelAttribute EmailDTO dto, Model model, HttpSession session) {
		
		logger.info("=====email send execute()...");
		logger.info("=====EmailDTO : ", dto.toString());
		
		ResponseEntity<String> entity = null;
		
		// 인증코드생성
		String authcode = "";
		for(int i=0; i<6; i++) {
			authcode += String.valueOf((int)(Math.random()*10));
		}
		// 인증코드 세션에 저장 : 사용자가 인증코드를 입력해서 요청이 발생되면 비교시 목적으로 세션에 저장.
		session.setAttribute("authcode", authcode);
		
		logger.info("=====authcode : " + authcode);
		try {
			emailservce.sendMail(dto, authcode);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.OK);
		}
		
		return entity;
	}
	
}

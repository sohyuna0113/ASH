package com.ashmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ashmall.domain.CartVO;
import com.ashmall.dto.MemberDTO;
import com.ashmall.service.CartService;

@Controller
@RequestMapping(value="/cart/*")
public class CartController {

	@Inject
	private CartService service;
	
	// 웹 프로젝트 영역 외부에 파일을 저장할 때 사용할 경로 
	@Resource(name="uploadPath")
	private String uploadPath; // servlet-context.xml에 설정 

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	/*
	 * 장바구니 추가(REST)
	 * 상품 1개 / 수량 1개 추가 
	 */
	@ResponseBody
	@RequestMapping(value="add", method=RequestMethod.POST)
	public ResponseEntity<String> addCart(int pd_num, HttpSession session){
		
		logger.info("=====add() execute");
		logger.info("=====pd_num: " + pd_num);
		
		ResponseEntity<String> entity = null;
		
		CartVO vo = new CartVO();
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		vo.setMb_id(dto.getMb_id());
		vo.setPd_num(pd_num);
		vo.setBuy_amount(1);
		logger.info("=====vo: "+ vo.toString());
		
		try {
			service.addCart(vo);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);		
		}
		return entity;
		}
	/*
	 * 장바구니에 추가(REST)
	 * 상품 1개 / 수량 여러개 추가 
	 * */

	@ResponseBody
	@RequestMapping(value="addMany", method=RequestMethod.POST)
	public ResponseEntity<String> addManyCart(int pd_num, int pd_amount, HttpSession session){
		
		logger.info("=====add() execute");
		logger.info("=====pd_num: " + pd_num);
		
		ResponseEntity<String> entity = null;
		
		CartVO vo = new CartVO();
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		vo.setMb_id(dto.getMb_id());
		vo.setPd_num(pd_num);
		vo.setBuy_amount(pd_amount);
		logger.info("=====vo: " + vo.toString());
		
		try {
			service.addCart(vo);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	/* 장바구니 삭제 */
	@ResponseBody
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public ResponseEntity<String> deleteCart(int cart_code) throws Exception {
		
		logger.info("=====delete() execute"); 
		
		ResponseEntity<String> entity = null;
		
		try {
			service.deleteCart(cart_code);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	/* 장바구니 선택 삭제 */
	
	@ResponseBody
	@RequestMapping(value="deleteChecked", method=RequestMethod.POST)
	public ResponseEntity<String> deleteChecked(@RequestParam("checkArr[]") List<Integer> checkArr) throws Exception {
		
		logger.info("=====deleteChecked() execute");
		
		ResponseEntity<String> entity = null;
		
		try {
			for(int cart_code : checkArr) {
				service.deleteCart(cart_code);
			}
			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	/* 장바구니 수량 수정 */
	@ResponseBody
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public ResponseEntity<String> modifyCart(int cart_code, int buy_amount) {
		
		logger.info("=====modify() execute");
		logger.info("=====cart_code: " + cart_code);
		logger.info("=====buy_amount: " + buy_amount);
		
		ResponseEntity<String> entity = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cart_code", cart_code);
		map.put("buy_amount", buy_amount);
		
		try {
			service.updateCart(map);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	/* 장바구니 목록 */
	@RequestMapping(value="list", method=RequestMethod.GET)
	public void listCartGET(Model model, HttpSession session) throws Exception {
		
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		model.addAttribute("cartProductList", service.getCart(dto.getMb_id()));
	}
	
}

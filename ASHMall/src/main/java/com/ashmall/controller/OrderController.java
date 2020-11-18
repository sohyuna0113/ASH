package com.ashmall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashmall.domain.OrderDetailVOList;
import com.ashmall.domain.OrderListVO;
import com.ashmall.domain.OrderVO;
import com.ashmall.domain.ProductVO;
import com.ashmall.dto.MemberDTO;
import com.ashmall.service.MemberService;
import com.ashmall.service.OrderService;
import com.ashmall.service.ProductService;


@Controller
@RequestMapping(value="/order/*")
public class OrderController {

	@Inject
	private OrderService service;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	/*
	상품 상세 -> 구매
	model객체로 상품리스트, 수량리스트, 구매자 정보전달 
	*/
	
	@RequestMapping(value="buy", method=RequestMethod.GET)
	public void buyGET(@RequestParam int pd_amount,
						@RequestParam int pd_num, Model model, HttpSession session) throws Exception{
		
		logger.info("=====buyGET() execute...");
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		List<Integer> amountList = new ArrayList<Integer>();
		
		productList.add(productService.readProduct(pd_num));
		amountList.add(pd_amount);
		
		model.addAttribute("productList", productList);
		model.addAttribute("amountList", amountList);
		
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		model.addAttribute("user", memberService.readUserInfo(dto.getMb_id()));
	}
	
	/*
	장바구니 -> 단일상품 구매
	model객체로 상품리스트, 수량리스트, 구매자 정보전달 
	*/
	
	@RequestMapping(value="buyFromCart", method=RequestMethod.GET)
	public String buyFromCartGET(@RequestParam int od_amount,
								  @RequestParam int pd_num, Model model, HttpSession session) throws Exception{
		
		logger.info("=====buyFromCartGET() execute...");
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		List<Integer> amountList = new ArrayList<Integer>();
		
		productList.add(productService.readProduct(pd_num));
		amountList.add(od_amount);
		
		model.addAttribute("productList", productList);
		model.addAttribute("amountList", amountList);
		
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		model.addAttribute("user", memberService.readUserInfo(dto.getMb_id()));	
		
		return "/order/buyFromCart";
	}
	
	/*
	장바구니 -> 다수상품 구매
	model객체로 상품리스트, 수량리스트, 구매자 정보전달  
	 */

	@RequestMapping(value="buyFromCart", method=RequestMethod.POST)
	public void buyFromCartPOST(@RequestParam("check") List<Integer> checkList,
							@RequestParam("pd_num") List<Integer> pd_numList,
							@RequestParam("buy_amount") List<Integer> cart_amountList,
							@RequestParam("cart_code") List<Integer> cart_codeList,
							Model model, HttpSession session) throws Exception {
	
		logger.info("=====buyFromCartPOST() execute...."); 
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		List<Integer> amountList = new ArrayList<Integer>();
		
		// 장바구니 목록에서 체크된 값만을 선택하여 List에 추가
		for(int i=0; i<cart_codeList.size(); i++) {
			for(int j=0; j<checkList.size(); j++) {
				// 2번 true
				if(cart_codeList.get(i) == checkList.get(j)) {
					// cart_amountList.get(i): 선택된 행의 변경된 수량 
					productList.add(productService.readProduct((int)pd_numList.get(i)));
					amountList.add(cart_amountList.get(i));
					continue;	// 제어는 안쪽의 for문의 j++
					
				} else {
					continue;
				}
				
			}
		
		}

		// 선택된 상품의 구매 페이지에서 필요한 Model작업 
		model.addAttribute("productList", productList);
		model.addAttribute("amountList", amountList);
		
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		model.addAttribute("user", memberService.readUserInfo(dto.getMb_id()));
		
	}
	
	/* 
	 * 상품 상세 -> 구매 또는 바로구매
	 * 실제 구매 DB작업
	 * 
	 * 기술 : jsp파일에서 파라미터 처리로   
	 * 		OrderDetailVOList orderDetailList 데이터 받는 방법
	 * <input type="hidden" id="amount_22" name="orderDetailList[0].odr_amount" value="1" />
	 * 
	 * 
	 * OrderVO order : 주문정보 1개 
	 * OrderDetailVOList orderDetailList : 주문상세정보 여러개 
	 * 
	 */
	@RequestMapping(value="order", method=RequestMethod.POST)
	public String orderPOST(OrderVO order, 
						  OrderDetailVOList orderDetailList,
						  HttpSession session) throws Exception {
		
		logger.info("=====orderPOST() execute...");
		
		logger.info("=====OrderVO(주문자 정보): " + order.toString());
		logger.info("=====OrderDetail(주문 내역): " + orderDetailList.toString());
		
		service.addOrder(order, orderDetailList);
		
		return "/order/orderComplete";
	}
	
	/* 
	 * 장바구니 -> 구매
	 * 실제 구매 DB작업
	 */
	@RequestMapping(value="orderFromCart", method=RequestMethod.POST)
	public String orderFromCartPOST(OrderVO order, 
						  OrderDetailVOList orderDetailList,
						  Model model, HttpSession session) throws Exception {
		
		logger.info("=====orderFromCartPOST() execute...");
		
		logger.info("=====OrderVO(주문자 정보): " + order.toString());
		logger.info("=====OrderDetail(주문 내역): " + orderDetailList.toString());
		
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		service.addOrderCart(order, orderDetailList, dto.getMb_id());
		
		return "/order/orderComplete";
	}

	/* 
	 * 주문 조회 작업(GET)
	 */
	@RequestMapping(value="list", method=RequestMethod.GET)
	public void listGET(Model model, HttpSession session) throws Exception {
		
		logger.info("=====listGET() execute...");
		
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		List<OrderListVO> list = service.orderList(dto.getMb_id());
		
		
		model.addAttribute("orderList", list);
		
	}
	
	/*
	 * 주문 상세 조회(GET)
	 */
	@RequestMapping(value="read", method=RequestMethod.GET)
	public void readGET(int od_num, Model model, HttpSession session) throws Exception {
		
		logger.info("=====readGET() execute...");
		
		model.addAttribute("orderList", service.readOrder(od_num));
		model.addAttribute("order", service.getOrder(od_num));
		
	}
}





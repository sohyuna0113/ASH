package com.ashmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashmall.domain.OrderDetailVO;
import com.ashmall.domain.OrderDetailVOList;
import com.ashmall.domain.OrderListVO;
import com.ashmall.domain.OrderReadDetailVO;
import com.ashmall.domain.OrderVO;
import com.ashmall.persistence.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO dao;
	
	@Autowired
	private CartService cartService;
	
	/*
		상품구매(상품상세/바로구매기능)
		주문내역과 주문정보추가
	
		사용자 1명이 여러건의 상품을 주문시
		메소드에서 2가지 이상의 기능이 사용될때, insert, update, delete경우 
	*/
	@Transactional
	@Override
	public void addOrder(OrderVO order, OrderDetailVOList orderDetailList) throws Exception {
		
		// 주문코드 시퀸스가져오기 
		int od_num = dao.readOrderCode();
	
		// 주문정보추가 
		order.setOd_num(od_num);
		dao.addOrder(order);
		// 주문테이블에 데이터삽입하기 
		
		// 주문내역추가. 주문상세테이블에 데이터삽입하기.
		List<OrderDetailVO> list = orderDetailList.getOrderDetailList();
		for(int i=0; i<list.size(); i++) {
			
			OrderDetailVO orderDetail = list.get(i);
			orderDetail.setOd_num(od_num);
			dao.addOrderDetail(orderDetail);
		}
		
	}

	/*
	  상품구매(장바구니)
	  주문내역과 주문정보 추가 
	  장바구니에서 넘어온 경우 장바구니 테이블에서 해당상품삭제 
	 */
	@Transactional
	@Override
	public void addOrderCart(OrderVO order, OrderDetailVOList orderDetailList, String mb_id) throws Exception {
		
		// 시퀀스(주문 코드) 가져오기
		int od_num = dao.readOrderCode();
		
		// 주문 정보 추가
		order.setOd_num(od_num);
		dao.addOrder(order);
		
		// 주문 내역 추가
		List<OrderDetailVO> list = orderDetailList.getOrderDetailList();
		for(int i=0; i<list.size(); i++) {
			
			OrderDetailVO orderDetail = list.get(i);
			orderDetail.setOd_num(od_num);
			dao.addOrderDetail(orderDetail);
			
			// 장바구니 테이블에서 해당 상품들 삭제
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mb_id", mb_id);
			map.put("pd_num", orderDetail.getPd_num());
			cartService.deleteCartOrder(map);
		}

	}
	
	// 주문목록 
	@Override
	public List<OrderListVO> orderList(String mb_id) throws Exception {
		return dao.orderList(mb_id);
	}

	// 주문상세정보 
	@Override
	public List<OrderReadDetailVO> readOrder(int od_num) throws Exception {
		return dao.readOrder(od_num);
	}

	// 주문자 정보 
	@Override
	public OrderVO getOrder(int od_num) throws Exception {
		return dao.getOrder(od_num);
	}

}

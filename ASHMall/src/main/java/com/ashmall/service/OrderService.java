package com.ashmall.service;

import java.util.List;

import com.ashmall.domain.OrderDetailVOList;
import com.ashmall.domain.OrderListVO;
import com.ashmall.domain.OrderReadDetailVO;
import com.ashmall.domain.OrderVO;

public interface OrderService {

	// 주문정보 추가(상품상세/바로구매)
	public void addOrder(OrderVO order, OrderDetailVOList orderDetailList) throws Exception;
	
	// 주문정보 추가(장바구니)
	public void addOrderCart(OrderVO order, OrderDetailVOList orderDetailList, String mb_id) throws Exception;
	
	// 주문목록
	public List<OrderListVO> orderList(String mb_id) throws Exception;
	
	// 주문 상세정보
	public List<OrderReadDetailVO> readOrder(int od_num) throws Exception;
	
	// 주문자 정보
	public OrderVO getOrder(int od_num) throws Exception;
	
	}


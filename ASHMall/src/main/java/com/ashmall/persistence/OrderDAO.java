package com.ashmall.persistence;

import java.util.List;

import com.ashmall.domain.OrderDetailVO;
import com.ashmall.domain.OrderListVO;
import com.ashmall.domain.OrderReadDetailVO;
import com.ashmall.domain.OrderVO;

public interface OrderDAO {

	// 주문코드 시퀸스 가져오기
	public int readOrderCode() throws Exception;
	
	// 주문내역 추가
	public void addOrderDetail(OrderDetailVO vo) throws Exception;
	
	// 주문정보 추가
	public void addOrder(OrderVO vo) throws Exception;
	
	// 주문목록
	public List<OrderListVO> orderList(String mb_id) throws Exception;
	
	// 주문상세정보
	public List<OrderReadDetailVO> readOrder(int od_num) throws Exception;
	
	// 주문자 정보
	public OrderVO getOrder(int od_num) throws Exception;
	
}

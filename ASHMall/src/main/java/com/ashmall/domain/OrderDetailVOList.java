package com.ashmall.domain;

import java.util.List;

/* List<> 컬렉션으로 데이터 받음 */

public class OrderDetailVOList {

	private List<OrderDetailVO> orderDetailList;

	/* Getter and Setter */
	public List<OrderDetailVO> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetailVO> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	/* toString() */
	@Override
	public String toString() {
		return "OrderDetailVOList [orderDetailList=" + orderDetailList + "]";
	}
	
}

package com.ashmall.domain;

public class OrderDetailVO {

	/*
	OD_NUM          NUMBER      NOT NULL,       -- 주문 날짜 정보를 이용하는 경우 
    PD_NUM          NUMBER      NOT NULL,      
    OD_AMOUNT       NUMBER      NOT NULL,
    OD_PRICE        NUMBER      NOT NULL,
	*/

	private int	od_num;
	private int	pd_num;
	private int	od_amount;
	private	int	od_price;
	
	/* Getter and Setter */
	public int getOd_num() {
		return od_num;
	}
	public void setOd_num(int od_num) {
		this.od_num = od_num;
	}
	public int getPd_num() {
		return pd_num;
	}
	public void setPd_num(int pd_num) {
		this.pd_num = pd_num;
	}
	public int getOd_amount() {
		return od_amount;
	}
	public void setOd_amount(int od_amount) {
		this.od_amount = od_amount;
	}
	public int getOd_price() {
		return od_price;
	}
	public void setOd_price(int od_price) {
		this.od_price = od_price;
	}
	
	/* toString() */
	@Override
	public String toString() {
		return "OrderDetailVO [od_num=" + od_num + ", pd_num=" + pd_num + ", od_amount=" + od_amount + ", od_price="
				+ od_price + "]";
	}
	
}

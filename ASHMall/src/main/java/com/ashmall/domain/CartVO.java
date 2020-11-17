package com.ashmall.domain;

public class CartVO {

	/*
	CART_CODE        NUMBER             PRIMARY KEY,
    PD_NUM           NUMBER             NOT NULL,
    MB_ID            VARCHAR2(15)       NOT NULL,
    BUY_AMOUNT       NUMBER             NOT NULL,
	 */
	
	private int		cart_code;
	private int		pd_num;
	private String	mb_id;
	private int		buy_amount;
	
	/* Getter and Setter */
	public int getCart_code() {
		return cart_code;
	}
	public void setCart_code(int cart_code) {
		this.cart_code = cart_code;
	}
	public int getPd_num() {
		return pd_num;
	}
	public void setPd_num(int pd_num) {
		this.pd_num = pd_num;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public int getBuy_amount() {
		return buy_amount;
	}
	public void setBuy_amount(int buy_amount) {
		this.buy_amount = buy_amount;
	}
	
	/* toString() */
	@Override
	public String toString() {
		return "CartVO [cart_code=" + cart_code + ", pd_num=" + pd_num + ", mb_id=" + mb_id + ", buy_amount="
				+ buy_amount + "]";
	}
	
}

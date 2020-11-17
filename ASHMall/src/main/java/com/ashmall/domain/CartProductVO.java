package com.ashmall.domain;

public class CartProductVO {

	/* 장바구니에 담긴 상품목록을 위한 VO객체 */
	
	private int 	cart_code;
	private int		buy_amount;
	private int		pd_num;
	private String	pd_name;
	private int		pd_price;
	private int		pd_dc;
	private String	pd_img;
	
	/* Getter and Setter */
	public int getCart_code() {
		return cart_code;
	}
	public void setCart_code(int cart_code) {
		this.cart_code = cart_code;
	}
	public int getbuy_amount() {
		return buy_amount;
	}
	public void setbuy_amount(int buy_amount) {
		this.buy_amount = buy_amount;
	}
	public int getPd_num() {
		return pd_num;
	}
	public void setPd_num(int pd_num) {
		this.pd_num = pd_num;
	}
	public String getPd_name() {
		return pd_name;
	}
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	public int getPd_price() {
		return pd_price;
	}
	public void setPd_price(int pd_price) {
		this.pd_price = pd_price;
	}
	public int getPd_dc() {
		return pd_dc;
	}
	public void setPd_dc(int pd_dc) {
		this.pd_dc = pd_dc;
	}
	public String getPd_img() {
		return pd_img;
	}
	public void setPd_img(String pd_img) {
		this.pd_img = pd_img;
	}
	
	/* toString() */
	@Override
	public String toString() {
		return "CartProductVO [cart_code=" + cart_code + ", buy_amount=" + buy_amount + ", pd_num=" + pd_num
				+ ", pd_name=" + pd_name + ", pd_price=" + pd_price + ", pd_dc=" + pd_dc + ", pd_img=" + pd_img + "]";
	}
	
	
}

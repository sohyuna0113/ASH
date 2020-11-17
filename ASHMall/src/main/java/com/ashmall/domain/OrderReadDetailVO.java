package com.ashmall.domain;

public class OrderReadDetailVO {

	private int		od_amount;
	private int		pd_num;
	private int		od_price;
	private int		pd_price;
	private String	pd_name;
	private String	pd_img;
	
	/* Getter and Setter */
	public int getOd_amount() {
		return od_amount;
	}
	public void setOd_amount(int od_amount) {
		this.od_amount = od_amount;
	}
	public int getPd_num() {
		return pd_num;
	}
	public void setPd_num(int pd_num) {
		this.pd_num = pd_num;
	}
	public int getOd_price() {
		return od_price;
	}
	public void setOd_price(int od_price) {
		this.od_price = od_price;
	}
	public int getPd_price() {
		return pd_price;
	}
	public void setPd_price(int pd_price) {
		this.pd_price = pd_price;
	}
	public String getPd_name() {
		return pd_name;
	}
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
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
		return "OrderReadDetailVO [od_amount=" + od_amount + ", pd_num=" + pd_num + ", od_price=" + od_price
				+ ", pd_price=" + pd_price + ", pd_name=" + pd_name + ", pd_img=" + pd_img + "]";
	}

}

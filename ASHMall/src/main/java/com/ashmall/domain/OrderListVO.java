package com.ashmall.domain;

import java.util.Date;

public class OrderListVO {

	private String	pd_img;
	private String	pd_name;
	private int 	od_num;
	private int		pd_num;
	private int		od_amount;
	private int		od_price;
	private Date	od_date;

	/* 추가된 부분 */
	private String	mb_id;
	private String	od_name;
	private String	od_zipcode;
	private String 	od_addr;
	private String	od_addr_d;
	private String 	od_phone;
	
	/* Getter and Setter */
	public String getPd_img() {
		return pd_img;
	}
	public void setPd_img(String pd_img) {
		this.pd_img = pd_img;
	}
	public String getPd_name() {
		return pd_name;
	}
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
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
	public Date getOd_date() {
		return od_date;
	}
	public void setOd_date(Date od_date) {
		this.od_date = od_date;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getOd_name() {
		return od_name;
	}
	public void setOd_name(String od_name) {
		this.od_name = od_name;
	}
	public String getOd_zipcode() {
		return od_zipcode;
	}
	public void setOd_zipcode(String od_zipcode) {
		this.od_zipcode = od_zipcode;
	}
	public String getOd_addr() {
		return od_addr;
	}
	public void setOd_addr(String od_addr) {
		this.od_addr = od_addr;
	}
	public String getOd_addr_d() {
		return od_addr_d;
	}
	public void setOd_addr_d(String od_addr_d) {
		this.od_addr_d = od_addr_d;
	}
	public String getOd_phone() {
		return od_phone;
	}
	public void setOd_phone(String od_phone) {
		this.od_phone = od_phone;
	}
	
	/* toString() */
	@Override
	public String toString() {
		return "OrderListVO [pd_img=" + pd_img + ", pd_name=" + pd_name + ", od_num=" + od_num + ", pd_num=" + pd_num
				+ ", od_amount=" + od_amount + ", od_price=" + od_price + ", od_date=" + od_date + ", mb_id=" + mb_id
				+ ", od_name=" + od_name + ", od_zipcode=" + od_zipcode + ", od_addr=" + od_addr + ", od_addr_d="
				+ od_addr_d + ", od_phone=" + od_phone + "]";
	}
	
}

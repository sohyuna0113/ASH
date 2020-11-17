package com.ashmall.domain;

import java.util.Date;

public class OrderVO {

	/*
    OD_NUM              NUMBER                  PRIMARY KEY,
    MB_ID               VARCHAR2(15)            NOT NULL,
    OD_NAME             VARCHAR2(30)            NOT NULL,
    OD_ZIPCODE          CHAR(5)                 NOT NULL,
    OD_ADDR             VARCHAR2(50)            NOT NULL,
    OD_ADDR_D           VARCHAR2(50)            NOT NULL,
    OD_PHONE            VARCHAR2(20)            NOT NULL,
    OD_PRICE            NUMBER                  NOT NULL,
    OD_DATE             DATE DEFAULT SYSDATE    NULL,
	*/
	
	private int		od_num;
	private String	mb_id;
	private String	od_name;
	private String	od_zipcode;
	private String	od_addr;
	private String	od_addr_d;
	private String 	od_phone;
	private int		od_price;
	private Date	od_date;
	
	/* Getter and Setter */
	public int getOd_num() {
		return od_num;
	}
	public void setOd_num(int od_num) {
		this.od_num = od_num;
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
	
	/* toString() */
	@Override
	public String toString() {
		return "OrderVO [od_num=" + od_num + ", mb_id=" + mb_id + ", od_name=" + od_name + ", od_zipcode=" + od_zipcode
				+ ", od_addr=" + od_addr + ", od_addr_d=" + od_addr_d + ", od_phone=" + od_phone + ", od_price="
				+ od_price + ", od_date=" + od_date + "]";
	}
	
	
}

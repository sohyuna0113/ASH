package com.ashmall.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ProductVO {

	/*
	CREATE TABLE PRODUCT_TB(
    PD_NUM              NUMBER                      PRIMARY KEY,    -- AUTO INCREMENT
    CATEG_NOW           VARCHAR2(20)                NOT NULL,       -- 2李? 
    CATEG_PRT           VARCHAR2(20)                NOT NULL,       -- 1李?    
    PD_NAME             VARCHAR2(50)                NOT NULL,
    PD_PRICE            NUMBER                      NOT NULL,
    PD_DC               NUMBER                      NOT NULL,
    PD_COMPANY          VARCHAR2(30)                NOT NULL,
    PD_DETAIL           CLOB                        NOT NULL,       -- CK Editor瑜? ?궗?슜 / HTML ?깭洹? ?삎?떇?쑝濡? ???옣 
    PD_IMG              VARCHAR(50)                 NOT NULL,
    PD_AMOUNT           NUMBER                      NOT NULL,
    PD_BUY              CHAR(1)                     NOT NULL,
    PD_SUBMIT           DATE                        NOT NULL,
    PD_UPDATE           DATE DEFAULT SYSDATE        NOT NULL,
    FOREIGN KEY(CATEG_NOW) REFERENCES CATEG_TB(CATEG_NOW)
    );
	 */
	
	private int 	pd_num;
	private String 	categ_now;
	private String 	categ_prt;
	private String 	pd_name;
	private int 	pd_price;
	private int		pd_dc;
	private String	pd_company;
	private String 	pd_detail;
	private String	pd_img;
	private int		pd_amount;
	private	String	pd_buy;
	private Date	pd_submit;
	private Date	pd_update;
	
	// 업로드 파일
	private MultipartFile file1;

	/* Getter and Setter */

	public int getPd_num() {
		return pd_num;
	}

	public void setPd_num(int pd_num) {
		this.pd_num = pd_num;
	}

	public String getCateg_now() {
		return categ_now;
	}

	public void setCateg_now(String categ_now) {
		this.categ_now = categ_now;
	}

	public String getCateg_prt() {
		return categ_prt;
	}

	public void setCateg_prt(String categ_prt) {
		this.categ_prt = categ_prt;
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

	public String getPd_company() {
		return pd_company;
	}

	public void setPd_company(String pd_company) {
		this.pd_company = pd_company;
	}

	public String getPd_detail() {
		return pd_detail;
	}

	public void setPd_detail(String pd_detail) {
		this.pd_detail = pd_detail;
	}

	public String getPd_img() {
		return pd_img;
	}

	public void setPd_img(String pd_img) {
		this.pd_img = pd_img;
	}

	public int getPd_amount() {
		return pd_amount;
	}

	public void setPd_amount(int pd_amount) {
		this.pd_amount = pd_amount;
	}

	public String getPd_buy() {
		return pd_buy;
	}

	public void setPd_buy(String pd_buy) {
		this.pd_buy = pd_buy;
	}

	public Date getPd_submit() {
		return pd_submit;
	}

	public void setPd_submit(Date pd_submit) {
		this.pd_submit = pd_submit;
	}

	public Date getPd_update() {
		return pd_update;
	}

	public void setPd_update(Date pd_update) {
		this.pd_update = pd_update;
	}

	public MultipartFile getFile1() {
		return file1;
	}

	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}

	/* toString() */

	@Override
	public String toString() {
		return "ProductVO [pd_num=" + pd_num + ", categ_now=" + categ_now + ", categ_prt=" + categ_prt + ", pd_name="
				+ pd_name + ", pd_price=" + pd_price + ", pd_dc=" + pd_dc + ", pd_company=" + pd_company
				+ ", pd_detail=" + pd_detail + ", pd_img=" + pd_img + ", pd_amount=" + pd_amount + ", pd_buy=" + pd_buy
				+ ", pd_submit=" + pd_submit + ", pd_update=" + pd_update + ", file1=" + file1 + "]";
	}
	
}


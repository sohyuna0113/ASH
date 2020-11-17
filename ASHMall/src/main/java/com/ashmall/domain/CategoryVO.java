package com.ashmall.domain;

/* 카테고리 테이블 */
public class CategoryVO {

	/*
			CATEG_TB(
		    CATEG_NOW           VARCHAR2(20)            PRIMARY KEY,
		    CATEG_PRT           VARCHAR2(20)            NULL,           
		    CATEG_NAME          VARCHAR2(50)            NOT NULL,
		    FOREIGN KEY(CATEG_NOW) REFERENCES CATEG_TB(CATEG_NOW)
		    );
	*/
	
	/* Getter and Setter */
	private String categ_now;
	private String categ_prt;
	private String categ_name;
	
	public String getCateg_now() {
		return categ_now;
	}
	public void setCateg_now(String categ_now) {
		this.categ_now = categ_now;
	}
	public String getcateg_prt() {
		return categ_prt;
	}
	public void setcateg_prt(String categ_prt) {
		this.categ_prt = categ_prt;
	}
	public String getcateg_name() {
		return categ_name;
	}
	public void setcateg_name(String categ_name) {
		this.categ_name = categ_name;
	}
	
	/* toString() */
	@Override
	public String toString() {
		return "CategoryVO [categ_now=" + categ_now + ", categ_prt=" + categ_prt + ", categ_name=" + categ_name + "]";
	}
	
}

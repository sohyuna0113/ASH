package com.ashmall.domain;

import java.util.Date;

public class ReviewVO {

	/*
 	RV_NUM          NUMBER                      PRIMARY KEY,    -- 시퀀스 생성
    MB_ID           VARCHAR2(15)                NOT NULL,
    PD_NUM          NUMBER                      NOT NULL,
    RV_CONTENT      VARCHAR2(200)               NOT NULL,
    RV_SCORE        NUMBER                      NOT NULL,
    RV_DATE         DATE DEFAULT SYSDATE        NOT NULL,
	*/
	
	private int		rv_num;
	private String	mb_id;
	private int		pd_num;
	private String	rv_content;
	private int		rv_score;
	private Date	rv_date;
	
	/* Getter and Setter */
	public int getRv_num() {
		return rv_num;
	}
	public void setRv_num(int rv_num) {
		this.rv_num = rv_num;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public int getPd_num() {
		return pd_num;
	}
	public void setPd_num(int pd_num) {
		this.pd_num = pd_num;
	}
	public String getRv_content() {
		return rv_content;
	}
	public void setRv_content(String rv_content) {
		this.rv_content = rv_content;
	}
	public int getRv_score() {
		return rv_score;
	}
	public void setRv_score(int rv_score) {
		this.rv_score = rv_score;
	}
	public Date getRv_date() {
		return rv_date;
	}
	public void setRv_date_reg(Date rv_date) {
		this.rv_date = rv_date;
	}
	
	/* toString() */
	@Override
	public String toString() {
		return "ReviewVO [rv_num=" + rv_num + ", mb_id=" + mb_id + ", pd_num=" + pd_num + ", rv_content=" + rv_content
				+ ", rv_score=" + rv_score + ", rv_date=" + rv_date + "]";
	}

}

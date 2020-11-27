package com.ashmall.domain;

import java.util.Date;

public class BoardVO {

	/*
	BD_NUM          NUMBER                      PRIMARY KEY,    -- 시퀀스 생성 
    MB_ID           VARCHAR2(15)                NOT NULL,
    BD_TITLE        VARCHAR2(100)               NOT NULL,
    BD_CONTENT      VARCHAR2(4000)              NOT NULL,
    BD_DATE         DATE DEFAULT SYSDATE        NOT NULL,
    FOREIGN KEY(MB_ID) REFERENCES MB_TB(MB_ID)
	 */
	
	private Integer bd_num;
	private String	mb_id;
	private String 	bd_title;
	private String	bd_content;
	private Date	bd_date;
	
	/* Getter and Setter */
	public Integer getBd_num() {
		return bd_num;
	}
	public void setBd_num(Integer bd_num) {
		this.bd_num = bd_num;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getBd_title() {
		return bd_title;
	}
	public void setBd_title(String bd_title) {
		this.bd_title = bd_title;
	}
	public String getBd_content() {
		return bd_content;
	}
	public void setBd_content(String bd_content) {
		this.bd_content = bd_content;
	}
	public Date getBd_date() {
		return bd_date;
	}
	public void setBd_date(Date bd_date) {
		this.bd_date = bd_date;
	}
	
	/* toString() */
	@Override
	public String toString() {
		return "BoardVO [bd_num=" + bd_num + ", mb_id=" + mb_id + ", bd_title=" + bd_title + ", bd_content="
				+ bd_content + ", bd_date=" + bd_date + "]";
	}
	
}

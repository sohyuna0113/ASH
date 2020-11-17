package com.ashmall.dto;

import java.util.Date;

public class MemberDTO {
	
	/*mb_id, mb_name, mb_password, mb_nickname, mb_point, mb_update*/
	
	private String mb_id;
	private String mb_name;
	private String mb_password;
	private String mb_nickname;
	private String mb_point;
	private Date mb_update;
	private boolean useCookie;
	
	/* getter and setter */
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_password() {
		return mb_password;
	}
	public void setMb_password(String mb_password) {
		this.mb_password = mb_password;
	}
	public String getMb_nickname() {
		return mb_nickname;
	}
	public void setMb_nickname(String mb_nickname) {
		this.mb_nickname = mb_nickname;
	}
	public String getMb_point() {
		return mb_point;
	}
	public void setMb_point(String mb_point) {
		this.mb_point = mb_point;
	}
	public Date getMb_update() {
		return mb_update;
	}
	public void setMb_update(Date mb_update) {
		this.mb_update = mb_update;
	}
	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	
	/* toString() */
	@Override
	public String toString() {
		return "MemberDTO [mb_id=" + mb_id + ", mb_name=" + mb_name + ", mb_password=" + mb_password + ", mb_nickname="
				+ mb_nickname + ", mb_point=" + mb_point + ", mb_update=" + mb_update + ", useCookie=" + useCookie
				+ "]";
	}
	
}

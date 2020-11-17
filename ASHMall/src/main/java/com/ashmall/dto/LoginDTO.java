package com.ashmall.dto;

public class LoginDTO {

	/* ID와 PW 입력정보를 저장용도 */
	
	private String 	mb_id;
	private String 	mb_password;
	private boolean isUseCookie;
	
	/* Getter and Setter */
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMb_password() {
		return mb_password;
	}
	public void setMb_password(String mb_password) {
		this.mb_password = mb_password;
	}
	public boolean isUseCookie() {
		return isUseCookie;
	}
	public void setUseCookie(boolean isUseCookie) {
		this.isUseCookie = isUseCookie;
	}
	
	/* toString() */
	@Override
	public String toString() {
		return "LoginDTO [mb_id=" + mb_id + ", mb_password=" + mb_password + ", isUseCookie=" + isUseCookie + "]";
	}
	
	
}

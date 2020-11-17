package com.ashmall.dto;

public class EmailDTO {
	
	private String senderName;		// 발신자 이름
	private String senderEmail;		// 발신자 이메일
	private String recieverEmail;	// 수신자 이메일
	private String subject;			// 제목
	private String message;			// 내용
	
	
	/* 생성자 메소드 */	public EmailDTO() {
		this.senderName = "ASHMall";
		this.senderEmail = "ASHMall";
		
		this.subject = "ASHMall AUTHORIZATION CODE";
		this.message = "TO VALIDATE YOUR EMAIL, PLEASE ENTER YOUR 6-DIGIT CODE.\n\n CODE: ";
	}
	
	/* getter and setter */
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public String getRecieverEmail() {
		return recieverEmail;
	}
	public void setRecieverEmail(String recieverEmail) {
		this.recieverEmail = recieverEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	/* toString */
	@Override
	public String toString() {
		return "EmailDTO [senderName=" + senderName + ", senderEmail=" + senderEmail + ", recieverEmail="
				+ recieverEmail + ", subject=" + subject + ", message=" + message + "]";
	}

}

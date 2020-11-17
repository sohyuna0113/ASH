package com.ashmall.service;

import javax.inject.Inject;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ashmall.dto.EmailDTO;

@Service
public class EmailServiceImpl implements EmailService {

	/* DI(의존성 주입구문) : root-context.xml에 설정 참고(JavaMailSenderImpl 클래스 bean개체). */
	@Inject
	JavaMailSender mailSender; 
	
	/* 이메일 인증 코드 전송 */
	
	@Override
	public void sendMail(EmailDTO dto, String authcode) {
		MimeMessage msg = mailSender.createMimeMessage();
		
		try {
			// 받는 사람 설정
			msg.addRecipient(RecipientType.TO, new InternetAddress(dto.getRecieverEmail()));
			// 보내는 사람 설정
			msg.addFrom(new InternetAddress[] {new InternetAddress(dto.getSenderEmail(), dto.getSenderName())});
			// 이메일 제목
			msg.setSubject(dto.getSubject(), "utf-8");	
			// 이메일 본문
			msg.setText(dto.getMessage() + authcode, "utf-8");
			
			mailSender.send(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

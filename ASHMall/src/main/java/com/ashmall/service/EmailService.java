package com.ashmall.service;

import com.ashmall.dto.EmailDTO;

public interface EmailService {

	public void sendMail(EmailDTO dto, String authcode);
}

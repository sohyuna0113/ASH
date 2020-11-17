package com.ashmall.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ashmall.controller.passwdEncryptController;

@Controller
@RequestMapping("/passwdEncrypt/*")
public class passwdEncryptController {

	private static final Logger logger = LoggerFactory.getLogger(passwdEncryptController.class);

	@Inject
	private BCryptPasswordEncoder passwdEncrypt;
	
	@RequestMapping(value="passwdEncrypt", method = RequestMethod.GET)
	public void encryptPasswordInfo(Model model) throws Exception {
		
		String ogi_pass = "1111";
		String enc_pass = passwdEncrypt.encode(ogi_pass);
		String enc_pass2 = passwdEncrypt.encode(ogi_pass);
		
		model.addAttribute("ogi_pass", ogi_pass);
		model.addAttribute("enc_pass", enc_pass);
		model.addAttribute("enc_pass2", enc_pass2);
	}
}

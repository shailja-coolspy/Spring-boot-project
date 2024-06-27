package com.bookRealm.api_v1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private JavaMailSender mailSender;
	
	
	@Autowired
    public EmailService(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}



	public void sendRegistrationSuccessEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("shailjaagarwal1970@gmail.com");
        mailSender.send(message);
    }

}

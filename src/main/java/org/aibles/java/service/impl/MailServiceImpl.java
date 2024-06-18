package org.aibles.java.service.impl;

import org.aibles.java.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendOTP(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your One-Time Password (OTP)");
        message.setText("Here is your OTP to access the service: " + otp);
        mailSender.send(message);
    }

    @Async
    public void sendSimpleMessage(String to, String subject, String text) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        }
    }


package org.aibles.java.event.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.java.event.EmailEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailEventListener {
    private final JavaMailSender mailSender;
    @EventListener
    @Async
    public void sendEmail(EmailEvent event) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getEmail());
        message.setSubject("Your One-Time Password (OTP)");
        message.setText("Here is your OTP to access the service: " + event.getOtp());
        mailSender.send(message);
    }
    }

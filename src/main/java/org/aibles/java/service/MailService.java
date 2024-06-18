package org.aibles.java.service;

public interface MailService {
    void sendOTP(String email, String otp);
    void sendSimpleMessage(String to, String subject, String text);
}

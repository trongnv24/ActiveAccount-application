package Aibles_Training.java.Spring.boot.active.account.service;

public interface MailService {
    void sendOTP(String email, String otp);
}

package org.aibles.java.service;

public interface RedisService {
    void saveOTP(String username, String otp);
    String findOtp(String username);
    void clearActiveOtp(String username);
}

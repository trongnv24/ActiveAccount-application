package Aibles_Training.java.Spring.boot.active.account.service;

public interface RedisService {
    void saveOTP(String username, String otp);
}

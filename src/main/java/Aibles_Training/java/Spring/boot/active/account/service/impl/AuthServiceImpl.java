package Aibles_Training.java.Spring.boot.active.account.service.impl;

import Aibles_Training.java.Spring.boot.active.account.dto.request.OtpRequest;
import Aibles_Training.java.Spring.boot.active.account.dto.response.OtpResponse;
import Aibles_Training.java.Spring.boot.active.account.entity.Account;
import Aibles_Training.java.Spring.boot.active.account.exception.InvalidRequestException;
import Aibles_Training.java.Spring.boot.active.account.exception.NotFoundException;
import Aibles_Training.java.Spring.boot.active.account.exception.InvalidOtpException;
import Aibles_Training.java.Spring.boot.active.account.repository.AuthRepository;
import Aibles_Training.java.Spring.boot.active.account.service.AuthService;
import Aibles_Training.java.Spring.boot.active.account.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final RedisService redisService;
    public AuthServiceImpl(AuthRepository authRepository, RedisService redisService) {
        this.authRepository = authRepository;
        this.redisService = redisService;
    }

    @Override
    public OtpResponse activeAccount(OtpRequest request) {
        String username = request.getUsername();
        String otp = request.getOtp();
        validateInput(username, otp);
        log.info("Looking for user account with username: {}", username);
        Optional<Account> otpAccount = authRepository.findUserAccountByUsername(username);
        if (otpAccount.isEmpty()) {
            log.error("Username was not registered", username);
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("message", "User not found");
            throw new NotFoundException(errorDetails);
        }

        Account account = otpAccount.get();
        if (account.isActivated()) {
            return new OtpResponse("success", System.currentTimeMillis(), null);
        }

        String storedOtp = redisService.findOtp(username);
        if (storedOtp == null) {
            throw new InvalidRequestException("OTP has expired");
        } else if (!storedOtp.equals(otp)) {
            throw new InvalidOtpException("OTP is invalid");
        }
        account.setActivated(true);
        authRepository.save(account);

        redisService.clearActiveOtp(username);

        return new OtpResponse("success", System.currentTimeMillis(), null);
    }

    private void validateInput(String username, String otp) {
        StringBuilder errors = new StringBuilder();
        if (username == null || username.isEmpty()) {
            errors.append("Username is required. ");
        }
        if (otp == null || otp.length() != 6) {
            errors.append("OTP is required and length must be 6.");
        }

    }
}


package org.aibles.java.service.impl;

import org.aibles.java.dto.request.OtpRequest;
import org.aibles.java.dto.response.BaseResponse;
import org.aibles.java.entity.Account;
import org.aibles.java.exception.InvalidRequestException;
import org.aibles.java.exception.NotFoundException;
import org.aibles.java.exception.InvalidOtpException;
import org.aibles.java.repository.AuthRepository;
import org.aibles.java.service.AuthService;
import org.aibles.java.service.RedisService;
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
    public BaseResponse activeAccount(OtpRequest request) {
        String username = request.getUsername();
        String otp = request.getOtp();
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
            return new BaseResponse<>("success", System.currentTimeMillis(), null);
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

        return new BaseResponse("success", System.currentTimeMillis(), null);
    }
    }


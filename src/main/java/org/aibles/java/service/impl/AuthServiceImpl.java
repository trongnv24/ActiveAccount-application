package org.aibles.java.service.impl;

import org.aibles.java.dto.request.OtpRequest;
import org.aibles.java.dto.response.BaseResponse;
import org.aibles.java.entity.Account;
import org.aibles.java.exception.InvalidRequestException;
import org.aibles.java.exception.NotFoundException;
import org.aibles.java.exception.InvalidOtpException;
import org.aibles.java.repository.UserRegisterRepository;
import org.aibles.java.service.AuthService;
import org.aibles.java.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRegisterRepository accountRepository;
    private final RedisService redisService;
    public AuthServiceImpl(UserRegisterRepository accountRepository, RedisService redisService) {
        this.accountRepository = accountRepository;
        this.redisService = redisService;
    }

    @Override
    public BaseResponse activeAccount(OtpRequest request) {
        String Username = request.getUsername();
        String otp = request.getOtp();
        log.info("Looking for user account with username: {}", Username);
        Optional<Account> otpAccount = accountRepository.findByUsername(Username);
        if (otpAccount.isEmpty()) {
            log.error("Username was not registered", Username);
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("message", "User not found");
            throw new NotFoundException(errorDetails);
        }

        Account account = otpAccount.get();
        if (account.isActivated()) {
            return new BaseResponse<>("success", System.currentTimeMillis(), null);
        }

        String storedOtp = redisService.findOtp(Username);
        if (storedOtp == null) {
            throw new InvalidRequestException("OTP has expired");
        } else if (!storedOtp.equals(otp)) {
            throw new InvalidOtpException("OTP is invalid");
        }
        account.setActivated(true);
        accountRepository.save(account);

        redisService.clearActiveOtp(Username);

        return new BaseResponse("success", System.currentTimeMillis(), null);
    }
    }


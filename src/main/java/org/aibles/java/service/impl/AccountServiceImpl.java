package org.aibles.java.service.impl;

import org.aibles.java.dto.request.ActiveAccountRequest;
import org.aibles.java.dto.response.BaseResponse;
import org.aibles.java.entity.Account;
import org.aibles.java.exception.InvalidRequestException;
import org.aibles.java.repository.AccountRepository;
import org.aibles.java.service.AccountService;
import org.aibles.java.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository activeAccountRepository;
    private final RedisService redisService;
    private final MailServiceImpl mailServiceImpl;

    public AccountServiceImpl(AccountRepository activeAccountRepository, RedisService redisService, MailServiceImpl mailServiceImpl) {
        this.activeAccountRepository = activeAccountRepository;
        this.redisService = redisService;
        this.mailServiceImpl = mailServiceImpl;
    }

    @Override
    public BaseResponse activeAccount(ActiveAccountRequest request) {
        log.info(" === Start api activeAccount === ");
        log.info(" === Request Body: {} === ", request);
        String username = request.getUsername();
        if (username == null || username.trim().isEmpty()) {
            throw new InvalidRequestException("Username cannot be blank or empty.");
        }
        log.info("Checking user existence in database.");
        Optional<Account> optionalActiveAccount = activeAccountRepository.findByUsername(username.trim());
        if (!optionalActiveAccount.isPresent()) {
            throw new InvalidRequestException("username: " + username + " not found or invalid");
        }
        String otp = generateOTP();
        redisService.saveOTP(username, otp);
        log.info("OTP {} saved to Redis for username {}", otp, username);
        mailServiceImpl.sendOTP(optionalActiveAccount.get().getEmail(), otp);
        log.info("OTP sent to email: {}", optionalActiveAccount.get().getEmail());
        log.info("optionalActiveAccount: {}", optionalActiveAccount.get().getId());
        Object data = null;
        return new BaseResponse("200", System.currentTimeMillis(), data);
    }
    private String generateOTP() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
    }

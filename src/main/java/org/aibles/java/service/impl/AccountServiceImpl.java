package org.aibles.java.service.impl;

import org.aibles.java.dto.request.ActiveAccountRequest;
import org.aibles.java.dto.response.BaseResponse;
import org.aibles.java.entity.UserProfile;
import org.aibles.java.exception.InvalidRequestException;
import org.aibles.java.repository.ActiveRepository;
import org.aibles.java.service.AccountService;
import org.aibles.java.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final ActiveRepository activeAccountRepository;
    private final RedisService redisService;
    private final MailServiceImpl mailServiceImpl;

    public AccountServiceImpl(ActiveRepository activeAccountRepository, RedisService redisService, MailServiceImpl mailServiceImpl) {
        this.activeAccountRepository = activeAccountRepository;
        this.redisService = redisService;
        this.mailServiceImpl = mailServiceImpl;
    }

    @Override
    public BaseResponse activeAccount(ActiveAccountRequest request) {
        log.info(" === Start api activeAccount === ");
        log.info(" === Request Body: {} === ", request);
        String name = request.getUsername();
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidRequestException("Username cannot be blank or empty.");
        }
        log.info("Checking user existence in database.");
        Optional<UserProfile> optionalActiveAccount = activeAccountRepository.findByName(name.trim());
        if (!optionalActiveAccount.isPresent()) {
            throw new InvalidRequestException("username: " + name + " not found or invalid");
        }
        String otp = generateOTP();
        redisService.saveOTP(name, otp);
        log.info("OTP {} saved to Redis for username {}", otp, name);
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

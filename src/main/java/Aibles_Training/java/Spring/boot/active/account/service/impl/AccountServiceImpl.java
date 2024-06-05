package Aibles_Training.java.Spring.boot.active.account.service.impl;

import Aibles_Training.java.Spring.boot.active.account.dto.request.ActiveAccountRequest;
import Aibles_Training.java.Spring.boot.active.account.dto.response.Response;
import Aibles_Training.java.Spring.boot.active.account.entity.Account;
import Aibles_Training.java.Spring.boot.active.account.repository.AccountRepository;
import Aibles_Training.java.Spring.boot.active.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository activeAccountRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final MailServiceImpl mailServiceImpl;

    public AccountServiceImpl(AccountRepository activeAccountRepository, RedisTemplate<String, String> redisTemplate, MailServiceImpl mailServiceImpl) {
        this.activeAccountRepository = activeAccountRepository;
        this.redisTemplate = redisTemplate;
        this.mailServiceImpl = mailServiceImpl;
    }

    @Override
    public Response activeAccount(ActiveAccountRequest request) {
        log.info(" === Start api activeAccount === ");
        log.info(" === Request Body: {} === ", request);
        String username = request.getUsername();
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be blank or empty.");
        }
        log.info("Checking user existence in database.");
        Optional<Account> optionalActiveAccount = activeAccountRepository.findByUsername(username.trim());
        if (!optionalActiveAccount.isPresent()) {
            throw new IllegalArgumentException("User not found for username: " + username);
        }
        String otp = generateOTP();
        String redisKey = "active_otp:" + username;
        redisTemplate.opsForValue().set(redisKey, otp, 3, TimeUnit.MINUTES);
        log.info("OTP {} saved to Redis for username {}", otp, username);
        mailServiceImpl.sendOTP(optionalActiveAccount.get().getEmail(), otp);
        log.info("OTP sent to email: {}", optionalActiveAccount.get().getEmail());
        log.info("optionalActiveAccount: {}", optionalActiveAccount.get().getId());
        Object data = null;
        return new Response("200", System.currentTimeMillis(), data);
    }

    private String generateOTP() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }
    }

package org.aibles.java.service.impl;

import jakarta.transaction.Transactional;


import lombok.extern.slf4j.Slf4j;
import org.aibles.java.constant.CacheConstant;
import org.aibles.java.dto.request.LoginRequest;
import org.aibles.java.dto.response.BaseResponse;
import org.aibles.java.dto.response.LoginResponse;
import org.aibles.java.entity.Account;
import org.aibles.java.entity.UserProfile;
import org.aibles.java.exception.NotFoundException;
import org.aibles.java.repository.LoginRepository;
import org.aibles.java.repository.UserRepository;
import org.aibles.java.service.JwtService;
import org.aibles.java.service.LoginService;
import org.aibles.java.service.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
public class LoginServiceImpl implements LoginService {
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RedisService redisService;
    private final JwtProperties jwtProperties;

    @Value("${expirationMs:}")
    private Long jwtExpirationMs;
    @Value("${refreshExpirationMs:}")
    private Long jwtRefreshExpirationMs;


    public LoginServiceImpl(LoginRepository loginRepository, PasswordEncoder passwordEncoder, JwtService jwtService, UserRepository userRepository, RedisService redisService, JwtProperties jwtProperties) {
        this.loginRepository = loginRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.redisService = redisService;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public BaseResponse<LoginResponse> login(LoginRequest loginRequest) {
        log.info("Login request: {}", loginRequest);

        Account account = loginRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new NotFoundException("Account not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), account.getPassword())) {
            log.info("Password does not match for username: {}", loginRequest.getUsername());
            throw new RuntimeException("Invalid password");
        }

        if (!account.isActivated()) {
            log.info("Account needs to be activated: {}", loginRequest.getUsername());
            throw new RuntimeException("Account needs to be activated");
        }

        UserProfile user = userRepository.findById(account.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        String accessToken = jwtService.generateAccessToken(account);
        String refreshToken = jwtService.generateRefreshToken(account);

        String accessTokenKey = user.getEmail() + "_" + CacheConstant.ACCESS_TOKEN_KEY;
        String refreshTokenKey = user.getEmail() + "_" + CacheConstant.REFRESH_TOKEN_KEY;

        redisService.save(accessTokenKey, accessToken, jwtProperties.getExpirationMs());
        redisService.save(refreshTokenKey, refreshToken, jwtProperties.getRefreshExpirationMs());

        log.info("Login successful for username: {}", loginRequest.getUsername());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(accessToken);
        loginResponse.setRefreshToken(refreshToken);
        loginResponse.setAccessTokenExpiration(jwtProperties.getExpirationMs());
        loginResponse.setRefreshTokenExpiration(jwtProperties.getRefreshExpirationMs());

        BaseResponse<LoginResponse> response = new BaseResponse<>();
        response.setCode("success");
        response.setTimestamp(System.currentTimeMillis());
        response.setData(loginResponse);

        return response;
    }
}
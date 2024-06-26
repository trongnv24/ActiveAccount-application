package org.aibles.java.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.aibles.java.entity.Account;
import org.aibles.java.exception.BadRequestException;
import org.aibles.java.repository.UserRegisterRepository;
import org.aibles.java.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList; 
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService {
    private final UserRegisterRepository userRegisterRepository;
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private Long jwtExpirationMs;

    @Value("${jwt.refreshExpirationMs}")
    private Long jwtRefreshExpirationMs;

    public JwtServiceImpl(UserRegisterRepository userRegisterRepository) {
        this.userRegisterRepository = userRegisterRepository;
    }

    private Key getSigningKey() {
       return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateAccessToken(Account account) {
        log.info("jwt secret {}", jwtSecret);
        return Jwts.builder()
                .setSubject(account.getUsername())
                .claim("id", account.getId())
                .claim("username", account.getUsername())
                .claim("isActivated", account.isActivated())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public String generateRefreshToken(Account account) {
        return Jwts.builder()
                .setSubject(account.getUsername())
                .claim("id", account.getId())
                .claim("username", account.getUsername())
                .claim("isActivated", account.isActivated())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtRefreshExpirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public String parseToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            throw new BadRequestException("Token is invalid");
        }
    }
    @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<Account> accountOptional = userRegisterRepository.findByUsername(username);
                if (accountOptional.isEmpty()) {
                    throw new UsernameNotFoundException("User not found");
                }
                Account account = accountOptional.get();
                return User.builder()
                        .username(account.getUsername())
                        .password(account.getPassword())
                        .authorities(new ArrayList<>())
                        .build();
            }
    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = parseToken(token);
        return username.equals(userDetails.getUsername());
    }
}
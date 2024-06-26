package org.aibles.java.service;

import org.aibles.java.entity.Account;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateAccessToken(Account account);
    String generateRefreshToken(Account account);
    String parseToken(String token);
    UserDetails loadUserByUsername(String username);
    boolean validateToken(String token, UserDetails userDetails);
}

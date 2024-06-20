package org.aibles.java.service;

import org.aibles.java.entity.Account;

public interface JwtService {
    String generateAccessToken(Account account);
    String generateRefreshToken(Account account);
    String parseToken(String token);
}

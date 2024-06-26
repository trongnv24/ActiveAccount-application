package org.aibles.java.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.java.entity.Account;
import org.aibles.java.repository.UserRegisterRepository;
import org.aibles.java.service.ChangePasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Slf4j
public class ChangePasswordServiceImpl implements ChangePasswordService {
    private final UserRegisterRepository userRegisterRepository;
    private final PasswordEncoder passwordEncoder;

    public ChangePasswordServiceImpl(UserRegisterRepository userRegisterRepository, PasswordEncoder passwordEncoder) {
        this.userRegisterRepository = userRegisterRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Optional<Account> account = userRegisterRepository.findByUsername(username);
        if (!account.isPresent() || !passwordEncoder.matches(oldPassword, account.get().getPassword())) {
            return false;
        }
        account.get().setPassword(passwordEncoder.encode(newPassword));
        userRegisterRepository.save(account.get());
        return true;
    }
}

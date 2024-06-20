package org.aibles.java.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.java.entity.Account;
import org.aibles.java.config.Constants;
import org.aibles.java.dto.request.UserRegisterRequest;
import org.aibles.java.dto.response.UserRegisterResponse;
import org.aibles.java.entity.Role;
import org.aibles.java.entity.UserProfile;
import org.aibles.java.entity.UserRole;
import org.aibles.java.event.EmailEvent;
import org.aibles.java.event.listener.EmailEventListener;
import org.aibles.java.repository.*;
import org.aibles.java.service.MailService;
import org.aibles.java.service.UserRegisterService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class UserRegisterServiceImpl implements UserRegisterService {
    private final UserRepository registerRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRegisterRepository accountRepository;
    private final ApplicationEventPublisher eventPublisher;

    public UserRegisterServiceImpl(UserRepository registerRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository, UserRegisterRepository accountRepository, ApplicationEventPublisher eventPublisher) {
        this.registerRepository = registerRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.accountRepository = accountRepository;
        this.eventPublisher = eventPublisher;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public UserRegisterResponse registerNewUse(UserRegisterRequest request) {
        Optional<UserProfile> optionalUserByEmail = registerRepository.findByEmail(request.getEmail());
        Optional<UserProfile> optionalUserByUsername = registerRepository.findByName(request.getName());
        if (optionalUserByEmail.isPresent() && optionalUserByUsername.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email và Tên đã được sử dụng");
        } else if (optionalUserByEmail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email đã được sử dụng");
        } else if (optionalUserByUsername.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tên đã được sử dụng");
        }

        String userId = UUID.randomUUID().toString();
        long currentTimestamp = Instant.now().getEpochSecond();

        UserProfile user = new UserProfile();
        user.setId(userId);
        user.setCreatedBy(userId);
        user.setCreateAt(currentTimestamp);
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user = registerRepository.save(user);

        Role defaultRole = roleRepository.findByName(Constants.USER_ROLE)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " không tìm thấy"));
        UserRole userRole = new UserRole(user.getId(), defaultRole.getId());
        userRoleRepository.save(userRole);

        Account account = new Account();
        account.setId(UUID.randomUUID().toString());
        account.setUserId(userId);
        account.setUsername(user.getName());
        account.setPassword(user.getPassword());
        account.setCreatedBy(userId);
        account.setActivated(false);
        account.setCreateAt(Instant.now().getEpochSecond());
        accountRepository.save(account);

        eventPublisher.publishEvent(new EmailEvent(this,request.getEmail(), generateOtp() ));

        UserRegisterResponse response = new UserRegisterResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }
    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}




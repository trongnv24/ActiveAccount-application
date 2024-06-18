package org.aibles.java.controller;

import jakarta.validation.Valid;
import org.aibles.java.dto.request.OtpRequest;
import org.aibles.java.dto.response.BaseResponse;
import org.aibles.java.dto.response.OtpResponse;
import org.aibles.java.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
public class AuthController {
    private final AuthService service;
    public AuthController(AuthService service) {
        this.service = service;
    }
    @PostMapping("/accounts/active-otp")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse activeAccount(@Valid @RequestBody OtpRequest request, BindingResult bindingResult) {
        log.info(" === Start api new account-active-otp === ");
        log.info(" === Request Body: {} === ", request);
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append(" "));
            throw new IllegalArgumentException(errors.toString().trim());
        }
        BaseResponse response = service.activeAccount(request);
        log.info("response: {}", response);
        log.info(" === Finish api account-active-otp, account-active-otp Id  {} === ");
        return response;
    }
}


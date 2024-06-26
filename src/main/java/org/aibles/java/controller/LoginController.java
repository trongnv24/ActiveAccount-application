package org.aibles.java.controller;

import lombok.extern.slf4j.Slf4j;
import org.aibles.java.dto.request.LoginRequest;
import org.aibles.java.dto.response.BaseResponse;
import org.aibles.java.dto.response.LoginResponse;
import org.aibles.java.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<BaseResponse<LoginResponse>> login(@Validated @RequestBody LoginRequest request) {
        log.info(" === Start api new login === ");
        log.info(" === Request Body : {} === ", request);
        BaseResponse<LoginResponse> loginResponse = loginService.login(request);
        log.info(" === Finish api new login === ");
        return ResponseEntity.ok(loginResponse);
    }
}

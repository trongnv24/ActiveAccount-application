package Aibles_Training.java.Spring.boot.active.account.controller;

import Aibles_Training.java.Spring.boot.active.account.dto.request.OtpRequest;
import Aibles_Training.java.Spring.boot.active.account.dto.response.OtpResponse;
import Aibles_Training.java.Spring.boot.active.account.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public OtpResponse activeAccount(@RequestBody OtpRequest request) {
        log.info(" === Start api new account-active-otp === ");
        log.info(" === Request Body: {} === ", request);
        OtpResponse response = service.activeAccount(request);
        log.info("response: {}", response);
        log.info(" === Finish api account-active-otp, account-active-otp Id  {} === ");
        return response;
    }
}


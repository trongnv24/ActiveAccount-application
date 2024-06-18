package org.aibles.java.controller;

import lombok.extern.slf4j.Slf4j;
import org.aibles.java.dto.request.UserRegisterRequest;
import org.aibles.java.dto.response.UserRegisterResponse;
import org.aibles.java.service.UserRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
@Validated
public class UserRegisterController {
    private final UserRegisterService service;

    public UserRegisterController(UserRegisterService service) {
        this.service = service;
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public UserRegisterResponse register(@RequestBody UserRegisterRequest request) {
        log.info(" === Start api new register === ");
        log.info(" === Request Body : {}  === ", request);
        UserRegisterResponse response = service.registerNewUse(request);
        log.info(" === Finish api new register, Register Id : {}  === ", response.getId());
        return response;
    }
    }
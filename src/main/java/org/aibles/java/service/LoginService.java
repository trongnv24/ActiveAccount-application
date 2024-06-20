package org.aibles.java.service;

import org.aibles.java.dto.request.LoginRequest;
import org.aibles.java.dto.response.BaseResponse;
import org.aibles.java.dto.response.LoginResponse;

public interface LoginService {
    BaseResponse<LoginResponse> login(LoginRequest loginRequest);
}

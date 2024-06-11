package org.aibles.java.service;

import org.aibles.java.dto.request.OtpRequest;
import org.aibles.java.dto.response.BaseResponse;
import org.aibles.java.dto.response.OtpResponse;

public interface AuthService {
    BaseResponse activeAccount(OtpRequest request);
}

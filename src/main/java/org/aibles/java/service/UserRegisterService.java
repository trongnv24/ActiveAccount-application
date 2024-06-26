package org.aibles.java.service;

import org.aibles.java.dto.request.UserRegisterRequest;
import org.aibles.java.dto.response.UserRegisterResponse;

public interface UserRegisterService {
    UserRegisterResponse registerNewUse(UserRegisterRequest request);
}

package Aibles_Training.java.Spring.boot.active.account.service;

import Aibles_Training.java.Spring.boot.active.account.dto.request.OtpRequest;
import Aibles_Training.java.Spring.boot.active.account.dto.response.OtpResponse;

public interface AuthService {
    OtpResponse activeAccount(OtpRequest request);
}

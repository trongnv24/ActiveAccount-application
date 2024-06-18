package org.aibles.java.service;

import org.aibles.java.dto.request.ActiveAccountRequest;
import org.aibles.java.dto.response.BaseResponse;

public interface AccountService {
     BaseResponse activeAccount(ActiveAccountRequest request);
}

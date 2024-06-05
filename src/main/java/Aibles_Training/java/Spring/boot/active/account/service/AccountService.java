package Aibles_Training.java.Spring.boot.active.account.service;

import Aibles_Training.java.Spring.boot.active.account.dto.request.ActiveAccountRequest;
import Aibles_Training.java.Spring.boot.active.account.dto.response.Response;

public interface AccountService {
     Response activeAccount(ActiveAccountRequest request);
}

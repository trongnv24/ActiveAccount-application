package Aibles_Training.java.Spring.boot.active.account.controller;

import Aibles_Training.java.Spring.boot.active.account.dto.request.ActiveAccountRequest;
import Aibles_Training.java.Spring.boot.active.account.dto.response.ActiveAccountResponse;
import Aibles_Training.java.Spring.boot.active.account.dto.response.Response;
import Aibles_Training.java.Spring.boot.active.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/account")
@Slf4j
public class AccountController {
    private final AccountService activeAccountService;

    public AccountController(AccountService activeAccountService) {
        this.activeAccountService = activeAccountService;
    }
    @PostMapping("/account-active")
    @ResponseStatus(HttpStatus.OK)
    public Response activeAccount(@RequestBody ActiveAccountRequest request){
        log.info(" === Start api activeAccount === ");
        log.info(" === Request Body: {} === ", request);
        Response response = activeAccountService.activeAccount(request);
        log.info("response: {}", response);
        log.info(" === Finish api activeAccount, ActiveAccount Id  {} === ");
        return response;
    }
}

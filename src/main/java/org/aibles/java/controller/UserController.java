package org.aibles.java.controller;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.aibles.java.dto.request.ChangePasswordRequest;
import org.aibles.java.service.ChangePasswordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
public class UserController {
    private final ChangePasswordService changePasswordService;

    public UserController(ChangePasswordService changePasswordService) {
        this.changePasswordService = changePasswordService;
    }

    @PostMapping("/change-password")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request, Authentication authentication) {
        String username = authentication.getName();
        boolean success = changePasswordService.changePassword(username, request.getOldPassword(), request.getNewPassword());
        if (success) {
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid old password");
        }
    }
}
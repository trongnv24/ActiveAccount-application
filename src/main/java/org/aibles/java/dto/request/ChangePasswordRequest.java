package org.aibles.java.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {
    @NotBlank(message = "USERNAME_REQUIRED")
    private String username;
    @NotBlank(message = "PASSWORD_REQUIRED")
    private String oldPassword;
    @NotBlank(message = "PASSWORD_REQUIRED")
    private String newPassword;

}

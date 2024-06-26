package org.aibles.java.dto.response;

import lombok.Data;

@Data
public class ChangePasswordResponse {
    private String message;
    private String token;

    public ChangePasswordResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }
}

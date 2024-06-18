package org.aibles.java.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ActiveAccountRequest {
    @NotBlank
    @NotNull
    private String username;
    public ActiveAccountRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ActiveAccountRequest{" +
                "username='" + getUsername() + '\'' +
                '}';
    }
}

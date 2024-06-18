package org.aibles.java.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class UserRegisterRequest {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String email;

    public UserRegisterRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "Name='" + getName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}

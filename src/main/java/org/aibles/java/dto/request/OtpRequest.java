package org.aibles.java.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OtpRequest {
    @NotNull
    @NotBlank
    private String username;
    private String otp;

    public OtpRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "OtpRequest{" +
                "username='" + getUsername() + '\'' +
                ", otp='" + getOtp() + '\'' +
                '}';
    }
}

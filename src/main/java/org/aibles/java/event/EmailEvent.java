package org.aibles.java.event;

import org.springframework.context.ApplicationEvent;


public class EmailEvent extends ApplicationEvent {

    private String email;
    private String otp;

    public EmailEvent(Object source, String email, String otp) {
        super(source);
        this.email = email;
        this.otp =otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}

package org.aibles.java.dto.response;

import org.aibles.java.dto.request.UserRegisterRequest;


public class UserRegisterResponse extends UserRegisterRequest {
    private String id;

    public UserRegisterResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "id=" + getId() +
                ", Name='" + getName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}

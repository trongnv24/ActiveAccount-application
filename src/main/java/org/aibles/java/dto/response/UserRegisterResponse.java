package org.aibles.java.dto.response;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterResponse  {
    @Id
    private String id;
    private String name;
    private String email;

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "id=" + getId() +
                ", Name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}

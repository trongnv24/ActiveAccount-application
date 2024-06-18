package org.aibles.java.entity;

import jakarta.persistence.*;
import org.aibles.java.entity.id.UserRoleId;

@Table(name = "user_role")
@Entity
@IdClass(UserRoleId.class)
public class UserRole {
    @Id
    private String userId;
    @Id
    private String roleId;

    public UserRole() {
    }

    public UserRole(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
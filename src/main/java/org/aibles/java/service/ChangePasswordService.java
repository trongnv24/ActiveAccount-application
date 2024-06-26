package org.aibles.java.service;




public interface ChangePasswordService {
    boolean changePassword(String username, String oldPassword, String newPassword);
}

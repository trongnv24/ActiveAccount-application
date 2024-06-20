package org.aibles.java.repository;

import org.aibles.java.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Account, String> {
    Optional<Account> findByUsername(String Username);
}

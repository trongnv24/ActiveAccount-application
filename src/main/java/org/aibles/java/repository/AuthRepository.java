package org.aibles.java.repository;

import org.aibles.java.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserName(String Username);
}

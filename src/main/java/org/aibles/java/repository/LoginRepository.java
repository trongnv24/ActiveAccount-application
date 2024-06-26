package org.aibles.java.repository;

import org.aibles.java.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Account, String> {
    Optional<Account> findByUsername(String Username);
}

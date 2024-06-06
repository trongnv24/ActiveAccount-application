package Aibles_Training.java.Spring.boot.active.account.repository;

import Aibles_Training.java.Spring.boot.active.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Account, Long> {
    Optional<Account> findUserAccountByUsername(String username);
}

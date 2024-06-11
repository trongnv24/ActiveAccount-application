package org.aibles.java.repository;

import org.aibles.java.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<org.aibles.java.entity.User, Long> {
    Optional<User> findByName(String name);
}

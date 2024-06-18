package org.aibles.java.repository;

import org.aibles.java.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, String> {
    Optional<UserProfile> findByEmail(String Email);
    Optional<UserProfile> findByName(String Name);
}

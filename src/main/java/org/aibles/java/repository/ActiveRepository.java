package org.aibles.java.repository;

import org.aibles.java.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActiveRepository extends JpaRepository<UserProfile, String> {
    Optional<UserProfile> findByName(String name);
}

package org.aibles.java.repository;

import org.aibles.java.entity.id.UserRoleId;
import org.aibles.java.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YourEntityRepository extends JpaRepository<UserRole, UserRoleId> {
}

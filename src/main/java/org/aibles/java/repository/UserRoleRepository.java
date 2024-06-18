package org.aibles.java.repository;

import org.aibles.java.entity.id.UserRoleId;
import org.aibles.java.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}

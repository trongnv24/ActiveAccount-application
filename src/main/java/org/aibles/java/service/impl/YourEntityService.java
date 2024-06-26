package org.aibles.java.service.impl;

import org.aibles.java.entity.id.UserRoleId;
import org.aibles.java.entity.UserRole;
import org.aibles.java.repository.YourEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class YourEntityService {
    @Autowired
    private YourEntityRepository repository;

    public UserRole saveEntity(UserRole entity) {
        return repository.save(entity);
    }
    public Optional<UserRole> getEntity(UserRoleId id) {
        return repository.findById(id);
    }
}

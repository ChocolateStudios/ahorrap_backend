package com.chocolatestudios.ahorrapp.contexts.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocolatestudios.ahorrapp.contexts.users.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);
    User findByUsername(String username);
}

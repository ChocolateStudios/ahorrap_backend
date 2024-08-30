package com.chocolatestudios.ahorrapp.contexts.profiles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocolatestudios.ahorrapp.contexts.profiles.models.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    boolean existsByFirstName(String firstName);
    Profile findByUserId(Long userId);
    boolean existsByEmail(String email);
}

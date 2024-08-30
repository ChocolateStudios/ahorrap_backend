package com.chocolatestudios.ahorrapp.contexts._shared.usecases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.chocolatestudios.ahorrapp.contexts._shared.exceptions.ForbiddenException;
import com.chocolatestudios.ahorrapp.contexts._shared.exceptions.ResourceNotFoundException;
import com.chocolatestudios.ahorrapp.contexts.profiles.models.Profile;
import com.chocolatestudios.ahorrapp.contexts.profiles.repositories.ProfileRepository;
import com.chocolatestudios.ahorrapp.contexts.users.models.User;
import com.chocolatestudios.ahorrapp.contexts.users.repositories.UserRepository;

public class AuthenticatedUserUseCase {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    protected Profile GetAuthenticatedProfileOrThrowException() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (username == null || username == "") {
            throw new ForbiddenException("Incorrectly registered user");
        }

        var user = userRepository.findByUsername(username);

        if (user == null) {
            throw new ResourceNotFoundException("User", "User Name", username);
        }

        var profile = profileRepository.findByUserId(user.getId());

        if (profile == null) {
            throw new ResourceNotFoundException("Profile", "User Id", user.getId());
        }

        return profile;
    }

    protected Optional<Profile> GetNullOrAuthenticatedProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (username == null || username == "") {
            throw new ForbiddenException("Incorrectly registered user");
        }

        var user = userRepository.findByUsername(username);

        if (user == null) {
            throw new ResourceNotFoundException("User", "User Name", username);
        }

        var profile = profileRepository.findByUserId(user.getId());
        return Optional.ofNullable(profile);
    }

    protected User GetAuthenticatedUserOrThrowException() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (username == null || username == "") {
            throw new ForbiddenException("Incorrectly registered user");
        }

        var user = userRepository.findByUsername(username);

        if (user == null) {
            throw new ResourceNotFoundException("User", "User Name", username);
        }

        return user;
    }
}

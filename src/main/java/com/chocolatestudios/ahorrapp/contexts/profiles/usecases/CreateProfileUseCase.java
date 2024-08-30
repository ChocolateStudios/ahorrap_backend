package com.chocolatestudios.ahorrapp.contexts.profiles.usecases;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts._shared.exceptions.ConflictException;
import com.chocolatestudios.ahorrapp.contexts._shared.usecases.AuthenticatedUserUseCase;
import com.chocolatestudios.ahorrapp.contexts.profiles.models.Profile;
import com.chocolatestudios.ahorrapp.contexts.profiles.repositories.ProfileRepository;
import com.chocolatestudios.ahorrapp.contexts.profiles.resources.ProfileResource;
import com.chocolatestudios.ahorrapp.contexts.profiles.resources.SaveProfileResource;

@Service
public class CreateProfileUseCase extends AuthenticatedUserUseCase {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileResource createProfile(SaveProfileResource saveProfileResource) {
        Optional<Profile> authenticatedProfile = GetNullOrAuthenticatedProfile();

        if (authenticatedProfile.isPresent()) {
            throw new ConflictException("Profile already exists for authenticated user");
        }

        if (profileRepository.existsByEmail(saveProfileResource.getEmail())) {
            throw new ConflictException("Profile", "Email", saveProfileResource.getEmail());
        }

        var authenticatedUser = GetAuthenticatedUserOrThrowException();

        Profile profile = mapper.map(saveProfileResource, Profile.class);
        profile.setUserId(authenticatedUser.getId());

        try {
            profileRepository.save(profile);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating the profile: " + e.getMessage());
        }

        return mapper.map(profile, ProfileResource.class);
    }
}

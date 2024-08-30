package com.chocolatestudios.ahorrapp.contexts.profiles.usecases;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts._shared.usecases.AuthenticatedUserUseCase;
import com.chocolatestudios.ahorrapp.contexts.profiles.models.Profile;
import com.chocolatestudios.ahorrapp.contexts.profiles.repositories.ProfileRepository;
import com.chocolatestudios.ahorrapp.contexts.profiles.resources.ProfileResource;
import com.chocolatestudios.ahorrapp.contexts.profiles.resources.SaveProfileResource;

@Service
public class UpdateProfileUseCase extends AuthenticatedUserUseCase {
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileResource updateProfile(SaveProfileResource saveProfileResource) {
        Profile existingProfile = GetAuthenticatedProfileOrThrowException();

        existingProfile.setFirstName(saveProfileResource.getFirstName());
        existingProfile.setLastName(saveProfileResource.getLastName());
        existingProfile.setEmail(saveProfileResource.getEmail());

        try {
            profileRepository.save(existingProfile);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the profile: " + e.getMessage());
        }

        return mapper.map(existingProfile, ProfileResource.class);
    }
}

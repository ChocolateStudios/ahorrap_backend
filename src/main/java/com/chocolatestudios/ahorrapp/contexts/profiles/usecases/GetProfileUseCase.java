package com.chocolatestudios.ahorrapp.contexts.profiles.usecases;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts._shared.usecases.AuthenticatedUserUseCase;
import com.chocolatestudios.ahorrapp.contexts.profiles.models.Profile;
import com.chocolatestudios.ahorrapp.contexts.profiles.resources.ProfileResource;

@Service
public class GetProfileUseCase extends AuthenticatedUserUseCase {
    @Autowired
    private ModelMapper mapper;

    public ProfileResource getProfile() {
        Profile existingProfile = GetAuthenticatedProfileOrThrowException();
        return mapper.map(existingProfile, ProfileResource.class);
    }
}

package com.chocolatestudios.ahorrapp.contexts.admins.usecases.profiles;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts.profiles.models.Profile;
import com.chocolatestudios.ahorrapp.contexts.profiles.repositories.ProfileRepository;
import com.chocolatestudios.ahorrapp.contexts.profiles.resources.ProfileResource;

@Service
public class GetAllProfilesUseCase {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProfileRepository profileRepository;

    public List<ProfileResource> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        List<ProfileResource> profileResources = profiles.stream().map(profile -> mapper.map(profile, ProfileResource.class)).toList();
        return profileResources;
    }
}

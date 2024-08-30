package com.chocolatestudios.ahorrapp.contexts.admins.usecases.profiles;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts.users.models.User;
import com.chocolatestudios.ahorrapp.contexts.users.repositories.UserRepository;
import com.chocolatestudios.ahorrapp.contexts.users.resources.SaveUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.resources.UserResource;

@Service
public class CreateProfilesUseCase {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;

    public List<UserResource> createProfiles(List<SaveUserResource> saveUserResources) {
        for (SaveUserResource saveUserResource : saveUserResources) {
            if (userRepository.existsByUsername(saveUserResource.getUsername()))
                throw new RuntimeException("User already exists");

            User user = mapper.map(saveUserResource, User.class);

            user = userRepository.save(user);
        }

        return saveUserResources.stream().map(saveUserResource -> mapper.map(saveUserResource, UserResource.class)).toList();
    }
}

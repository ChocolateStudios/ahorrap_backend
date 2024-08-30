package com.chocolatestudios.ahorrapp.contexts.admins.usecases.profiles;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts.users.models.User;
import com.chocolatestudios.ahorrapp.contexts.users.repositories.UserRepository;
import com.chocolatestudios.ahorrapp.contexts.users.resources.UserResource;

@Service
public class GetAllProfilesUseCase {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;

    public List<UserResource> getAllProfiles() {
        List<User> users = userRepository.findAll();
        List<UserResource> userResources = users.stream().map(user -> mapper.map(user, UserResource.class)).toList();

        return userResources;
    }
}

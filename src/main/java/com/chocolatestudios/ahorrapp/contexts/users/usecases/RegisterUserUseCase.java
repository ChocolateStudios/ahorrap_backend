package com.chocolatestudios.ahorrapp.contexts.users.usecases;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts.users.models.User;
import com.chocolatestudios.ahorrapp.contexts.users.repositories.UserRepository;
import com.chocolatestudios.ahorrapp.contexts.users.resources.SaveUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.resources.UserResource;

@Service
public class RegisterUserUseCase {
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private UserRepository userRepository;

    public UserResource registerUser(SaveUserResource saveUserResource) {
        if (userRepository.existsByEmail(saveUserResource.getEmail()))
            throw new RuntimeException("User already exists");

        User user = mapper.map(saveUserResource, User.class);

        userRepository.save(user);

        return mapper.map(user, UserResource.class);
    }
}

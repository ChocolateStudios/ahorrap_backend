package com.chocolatestudios.ahorrapp.contexts.users.usecases;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts.users.models.User;
import com.chocolatestudios.ahorrapp.contexts.users.repositories.UserRepository;
import com.chocolatestudios.ahorrapp.contexts.users.resources.SaveUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.resources.UserResource;

@Service
public class LoginUserUseCase {
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private UserRepository userRepository;

    public UserResource loginUser(SaveUserResource saveUserResource) {
        User existingUser = userRepository.findByEmail(saveUserResource.getEmail());

        if (existingUser == null)
            throw new RuntimeException("User not found");

        if (!existingUser.getPassword().equals(saveUserResource.getPassword()))
            throw new RuntimeException("Invalid password");

        return mapper.map(existingUser, UserResource.class);
    }
}

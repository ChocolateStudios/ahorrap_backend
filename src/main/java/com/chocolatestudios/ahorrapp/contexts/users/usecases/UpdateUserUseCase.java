package com.chocolatestudios.ahorrapp.contexts.users.usecases;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts.users.models.User;
import com.chocolatestudios.ahorrapp.contexts.users.repositories.UserRepository;
import com.chocolatestudios.ahorrapp.contexts.users.resources.SaveUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.resources.UserResource;

@Service
public class UpdateUserUseCase {
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private UserRepository userRepository;

    public UserResource updateUser(SaveUserResource saveUserResource) {
        User existingUser = userRepository.findByEmail(saveUserResource.getEmail());

        if (existingUser == null)
            throw new RuntimeException("User not found");

        // User user = mapper.map(saveUserResource, User.class);
        existingUser.setEmail(saveUserResource.getEmail());
        existingUser.setPassword(saveUserResource.getPassword());

        userRepository.save(existingUser);

        return mapper.map(existingUser, UserResource.class);
    }
}

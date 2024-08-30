package com.chocolatestudios.ahorrapp.contexts.users.usecases;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp._utils.JwtUtils;
import com.chocolatestudios.ahorrapp.contexts._shared.exceptions.BadRequestException;
import com.chocolatestudios.ahorrapp.contexts._shared.exceptions.ConflictException;
import com.chocolatestudios.ahorrapp.contexts._shared.usecases.AuthenticatedUserUseCase;
import com.chocolatestudios.ahorrapp.contexts.users.models.User;
import com.chocolatestudios.ahorrapp.contexts.users.repositories.UserRepository;
import com.chocolatestudios.ahorrapp.contexts.users.resources.AuthenticatedUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.resources.SaveUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.resources.UserResource;

@Service
public class UpdateUserPasswordUseCase extends AuthenticatedUserUseCase {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResource updateUserPassword(SaveUserResource saveUserResource) {
        User authenticatedUser = GetAuthenticatedUserOrThrowException();

        // if (authenticatedUser.getUsername() == saveUserResource.getUsername())
        //     throw new BadRequestException("Username cannot be the same");
        
        // User existingOtherUser = userRepository.findByUsername(saveUserResource.getUsername());

        // if (existingOtherUser != null)
        //     throw new ConflictException("User", "username", saveUserResource.getUsername());

        authenticatedUser.setPassword(passwordEncoder.encode(saveUserResource.getPassword()));

        try {
            userRepository.save(authenticatedUser);
        } catch (Exception e) {
            throw new BadRequestException("An error occurred while updating the user: " + e.getMessage());
        }

        var resource = mapper.map(authenticatedUser, UserResource.class);

        return resource;
    }
}

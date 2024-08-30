package com.chocolatestudios.ahorrapp.contexts.users.usecases;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp._utils.JwtUtils;
import com.chocolatestudios.ahorrapp.contexts._shared.exceptions.BadRequestException;
import com.chocolatestudios.ahorrapp.contexts._shared.exceptions.ConflictException;
import com.chocolatestudios.ahorrapp.contexts._shared.usecases.AuthenticatedUserUseCase;
import com.chocolatestudios.ahorrapp.contexts.users.models.User;
import com.chocolatestudios.ahorrapp.contexts.users.repositories.UserRepository;
import com.chocolatestudios.ahorrapp.contexts.users.resources.AuthenticatedUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.resources.SaveUserResource;

@Service
public class UpdateUserUsernameUseCase extends AuthenticatedUserUseCase {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsService userDetailsService;

    public AuthenticatedUserResource updateUserUsername(SaveUserResource saveUserResource) {
        User authenticatedUser = GetAuthenticatedUserOrThrowException();

        if (authenticatedUser.getUsername() == saveUserResource.getUsername())
            throw new BadRequestException("Username cannot be the same");
        
        User existingOtherUser = userRepository.findByUsername(saveUserResource.getUsername());

        if (existingOtherUser != null)
            throw new ConflictException("User", "username", saveUserResource.getUsername());

        authenticatedUser.setUsername(saveUserResource.getUsername());

        try {
            userRepository.save(authenticatedUser);
        } catch (Exception e) {
            throw new BadRequestException("An error occurred while updating the user: " + e.getMessage());
        }

        var userDetails = userDetailsService.loadUserByUsername(authenticatedUser.getUsername());
        String token = jwtUtils.generateToken(userDetails);

        var resource = mapper.map(authenticatedUser, AuthenticatedUserResource.class);
        resource.setToken(token);

        return resource;
    }
}

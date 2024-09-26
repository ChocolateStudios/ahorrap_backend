package com.chocolatestudios.ahorrapp.contexts.users.usecases;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts._shared.exceptions.BadRequestException;
import com.chocolatestudios.ahorrapp.contexts._shared.exceptions.ConflictException;
import com.chocolatestudios.ahorrapp.contexts._shared.utils.JwtUtils;
import com.chocolatestudios.ahorrapp.contexts.users.models.User;
import com.chocolatestudios.ahorrapp.contexts.users.repositories.UserRepository;
import com.chocolatestudios.ahorrapp.contexts.users.resources.AuthenticatedUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.resources.SaveUserResource;

@Service
public class RegisterUserUseCase {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticatedUserResource registerUser(SaveUserResource saveUserResource) {
        if (userRepository.existsByUsername(saveUserResource.getUsername()))
            throw new ConflictException("User", "username", saveUserResource.getUsername());

        User user = mapper.map(saveUserResource, User.class);

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch (Exception e) {
            throw new BadRequestException("An error occurred while saving the use: " + e.getMessage());
        }
        
        var userDetails = userDetailsService.loadUserByUsername(saveUserResource.getUsername());
        String token = jwtUtils.generateToken(userDetails);

        var resource = mapper.map(user, AuthenticatedUserResource.class);
        resource.setToken(token);

        return resource;
    }
}

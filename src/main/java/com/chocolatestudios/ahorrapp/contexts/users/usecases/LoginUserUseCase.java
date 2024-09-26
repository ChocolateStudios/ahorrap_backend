package com.chocolatestudios.ahorrapp.contexts.users.usecases;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts._shared.exceptions.BadCredentialsException;
import com.chocolatestudios.ahorrapp.contexts._shared.utils.JwtUtils;
import com.chocolatestudios.ahorrapp.contexts.users.models.User;
import com.chocolatestudios.ahorrapp.contexts.users.repositories.UserRepository;
import com.chocolatestudios.ahorrapp.contexts.users.resources.AuthenticatedUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.resources.SaveUserResource;

@Service
public class LoginUserUseCase {
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

    public AuthenticatedUserResource loginUser(SaveUserResource saveUserResource) {
        User existingUser = userRepository.findByUsername(saveUserResource.getUsername());

        if (existingUser == null)
            throw new BadCredentialsException();

        if (!passwordEncoder.matches(saveUserResource.getPassword(), existingUser.getPassword()))
            throw new BadCredentialsException();

        var userDetails = userDetailsService.loadUserByUsername(saveUserResource.getUsername());
        String token = jwtUtils.generateToken(userDetails);

        var resource = mapper.map(existingUser, AuthenticatedUserResource.class);
        resource.setToken(token);

        return resource;
    }
}

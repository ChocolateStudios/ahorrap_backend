package com.chocolatestudios.ahorrapp.contexts.users.usecases;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts._shared.usecases.AuthenticatedUserUseCase;
import com.chocolatestudios.ahorrapp.contexts.users.models.User;
import com.chocolatestudios.ahorrapp.contexts.users.resources.UserResource;

@Service
public class GetUserUseCase extends AuthenticatedUserUseCase {
    @Autowired
    private ModelMapper mapper;
    
    public UserResource getUser() {
        User authenticatedUser = GetAuthenticatedUserOrThrowException();
        return mapper.map(authenticatedUser, UserResource.class);
    }
}

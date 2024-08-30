package com.chocolatestudios.ahorrapp._configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chocolatestudios.ahorrapp.contexts.profiles.models.Profile;
import com.chocolatestudios.ahorrapp.contexts.profiles.resources.ProfileResource;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        
        mapper.addMappings(new PropertyMap<Profile, ProfileResource>() {
            @Override
            protected void configure() {
                map().setUserId(source.getUserId());
            }
        });

        return mapper;
    }
}

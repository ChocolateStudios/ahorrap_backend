package com.chocolatestudios.ahorrapp._configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chocolatestudios.ahorrapp.contexts.profiles.models.Profile;
import com.chocolatestudios.ahorrapp.contexts.profiles.resources.ProfileResource;
import com.chocolatestudios.ahorrapp.contexts.expenses.models.Expense;
import com.chocolatestudios.ahorrapp.contexts.expenses.resources.ExpenseResource;

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
        
        mapper.addMappings(new PropertyMap<Expense, ExpenseResource>() {
            @Override
            protected void configure() {
                map().setProfileId(source.getProfileId());
            }
        });

        return mapper;
    }
}

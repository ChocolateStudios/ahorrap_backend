package com.chocolatestudios.ahorrapp._configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi(
            @Value("${documentation.application.description}") String applicationDescription,
            @Value("${documentation.application.version}") String applicationVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("AhorrApp'e API")
                        .version(applicationVersion)
                        .description(applicationDescription))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
                // .components(new Components()
                //         .addSecuritySchemes("X-CSRF-TOKEN", new SecurityScheme()
                //                 .type(SecurityScheme.Type.APIKEY)
                //                 .in(SecurityScheme.In.HEADER)
                //                 .name("X-CSRF-TOKEN")))
                // .addSecurityItem(new SecurityRequirement().addList("X-CSRF-TOKEN"));
    }
}

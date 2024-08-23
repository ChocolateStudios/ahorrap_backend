package com.chocolatestudios.ahorrapp.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // http
        //     .authorizeHttpRequests((requests) -> requests
        //         .requestMatchers(new AntPathRequestMatcher("/api-docs")).permitAll()
        //         .requestMatchers(new AntPathRequestMatcher("/swagger-ui")).permitAll()
        //         .anyRequest().authenticated())
        //     .httpBasic();
        // http
        //     .authorizeHttpRequests((requests) -> requests
        //                 .anyRequest().permitAll()) // Permite todas las solicitudes sin autenticación
        //     .cors(cors -> cors
        //            .configurationSource(request -> {
        //                CorsConfiguration config = new CorsConfiguration();
        //                config.setAllowedOrigins(List.of("http://localhost:4200")); // Orígenes permitidos
        //                config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // Métodos permitidos
        //                config.setAllowedHeaders(List.of("*")); // Cabeceras permitidas
        //                return config;
        //            })
        //        );

                http
                    .cors(cors -> cors
                        .configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(List.of("*")); // Orígenes permitidos
                            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")); // Métodos permitidos
                            config.setAllowedHeaders(List.of("*")); // Cabeceras permitidas
                            return config;
                        })
                    )
                    .csrf().disable()
                    .authorizeHttpRequests((requests) -> requests
                        .anyRequest().permitAll()
                    );
        return http.build();
    }
}

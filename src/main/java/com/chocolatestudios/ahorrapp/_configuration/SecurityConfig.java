package com.chocolatestudios.ahorrapp._configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.chocolatestudios.ahorrapp._middleware.CustomAuthenticationEntryPoint;
import com.chocolatestudios.ahorrapp._middleware.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${cors.allowed.origins}") 
    private List<String> allowedOrigins;
    @Value("${security.excluded_endpoints}")
    private List<String> excludedEndpoints;
    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    @Autowired
    private CustomAuthenticationEntryPoint customEntryPoint;
    
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
                http
                    .cors(cors -> cors
                        .configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(allowedOrigins); // Orígenes permitidos
                            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")); // Métodos permitidos
                            config.setAllowedHeaders(List.of("*")); // Cabeceras permitidas
                            return config;
                        })
                    )
                    .csrf(csrf -> csrf
                        .disable()
                    )
                    .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/swagger-ui/**", "/api-docs/**").permitAll()
                        .requestMatchers(excludedEndpoints.toArray(new String[0])).permitAll()
                        .anyRequest().authenticated()
                    )
                    .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(customEntryPoint)
                    )
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.chocolatestudios.ahorrapp._middleware;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.chocolatestudios.ahorrapp.contexts._shared.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    @Value("${security.excluded_endpoints}")
    private String excludedEndpoints;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
                
        try {
            String authHeader = request.getHeader("Authorization");
            String username = null;
            String jwtToken = null;

            if (authHeader != null && authHeader.startsWith("Bearer ") && !isExcludedEndpoint(request)) {
                jwtToken = authHeader.substring(7);
                username = jwtUtils.extractUsername(jwtToken);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtUtils.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken auth = jwtUtils.getAuthentication(jwtToken, userDetails);
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            System.out.println("***********************************************************************");
            System.out.println("INFO: An error occurred in jwt filter while processing the request: " + e.getMessage());
            System.out.println("***********************************************************************");
            throw e;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isExcludedEndpoint(HttpServletRequest request) {
        String[] endpoints = excludedEndpoints.split(",");
        return Arrays.stream(endpoints)
                .anyMatch(endpoint -> request.getRequestURI().startsWith(endpoint));
    }
}

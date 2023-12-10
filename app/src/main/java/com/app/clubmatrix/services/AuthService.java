package com.app.clubmatrix.services;

import com.app.clubmatrix.services.dto.CredentialsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public AuthService() {
    }

    public boolean authenticateUser(CredentialsDTO credentials) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.getUsername());
        return  passwordEncoder.matches(new String(credentials.getPassword()), userDetails.getPassword());
    }

    public void authorizeUser() {
    }
}
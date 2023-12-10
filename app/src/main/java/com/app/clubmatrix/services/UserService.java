package com.app.clubmatrix.services;

import com.app.clubmatrix.models.User;
import com.app.clubmatrix.repositories.UserRepository;
import com.app.clubmatrix.services.dto.CredentialsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User createUser(CredentialsDTO credentials) {
        User newUser = new User();

        newUser.setUsername(credentials.getUsername());
        newUser.setPassword(passwordEncoder.encode(new String(credentials.getPassword())));

        return userRepository.save(newUser);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
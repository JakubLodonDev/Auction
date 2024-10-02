package com.auction.dev.service;

import com.auction.dev.model.User;
import com.auction.dev.request.UpdateUserRequest;
import com.auction.dev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserDetails() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User updateUserDetails(UpdateUserRequest updateUserRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setFirstname(updateUserRequest.getFirstname());
        user.setLastname(updateUserRequest.getLastname());
        user.setEmail(updateUserRequest.getEmail());
        if (!updateUserRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
        }

        return userRepository.save(user);
    }
}


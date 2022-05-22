package com.example.employeesoap.service;

import com.example.employeesoap.entity.User;
import com.example.employeesoap.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("{0} not found", username)));
        return getUserDetails(user);
    }

    private UserDetailsImpl getUserDetails(User user) {
        return UserDetailsImpl.build(user);
    }
}

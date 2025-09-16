package com.springboot.mvc.service.impl;

import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.mvc.dto.RegistrationDto;
import com.springboot.mvc.models.Role;
import com.springboot.mvc.models.UserEntity;
import com.springboot.mvc.repository.RoleRepository;
import com.springboot.mvc.repository.UserRepository;
import com.springboot.mvc.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(RegistrationDto registrationDto) {

        UserEntity user = new UserEntity();
        Role role = roleRepository.findByName("USER");

        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);

    }

    @Override
    public UserEntity findByEmail(String email) {

        return userRepository.findByEmail(email);

    }

    @Override
    public UserEntity findByUsername(String username) {

        return userRepository.findByUsername(username);

    }

}

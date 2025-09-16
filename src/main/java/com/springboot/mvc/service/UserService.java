package com.springboot.mvc.service;

import com.springboot.mvc.dto.RegistrationDto;
import com.springboot.mvc.models.UserEntity;

public interface UserService {

    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

}

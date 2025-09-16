package com.springboot.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.mvc.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    UserEntity findFirstByUsername(String username);

}

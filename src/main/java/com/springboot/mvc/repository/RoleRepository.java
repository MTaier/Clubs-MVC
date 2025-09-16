package com.springboot.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.mvc.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}

package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

}

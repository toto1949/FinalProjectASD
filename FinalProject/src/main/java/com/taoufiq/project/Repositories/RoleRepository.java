package com.taoufiq.project.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taoufiq.project.Models.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
    public abstract Optional<Role> findByName(String name);
}


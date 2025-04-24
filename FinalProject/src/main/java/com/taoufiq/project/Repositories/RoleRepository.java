package com.taoufiq.project.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taoufiq.project.Models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public abstract Optional<Role> findByName(String name);
}


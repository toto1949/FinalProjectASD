package com.taoufiq.project.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taoufiq.project.Models.Role;
import com.taoufiq.project.Repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        Optional<Role> roleOpt = roleRepository.findByName(name);
        return roleOpt.orElseThrow(() -> new RuntimeException("Role not found: " + name));
    }
}

package com.taoufiq.project.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taoufiq.project.Models.Role;
import com.taoufiq.project.Models.User;
import com.taoufiq.project.Repositories.RoleRepository;
import com.taoufiq.project.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken.");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use.");
        }
        System.out.println("password in userservice"+ user.getPassword() == null);

        if (user.getPassword() == null) {
            throw new RuntimeException("Password cannot be null");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
    
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Role defaultRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
            user.setRoles(List.of(defaultRole));
        } else {
            List<Role> managedRoles = user.getRoles().stream()
                .map(role -> roleRepository.findByName(role.getName())
                    .orElseThrow(() -> new RuntimeException("Role not found: " + role.getName())))
                .collect(Collectors.toList());
            user.setRoles(managedRoles);
        }
    
        return userRepository.save(user);
    }
    

}
